/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelFuture
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.channel.ChannelInboundHandlerAdapter
 *  io.netty.util.concurrent.EventExecutor
 *  io.netty.util.concurrent.ScheduledFuture
 */
package protocolsupport.protocol.core.timeout;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import protocolsupport.protocol.core.timeout.FirstReadTimeoutException;
import protocolsupport.protocol.core.timeout.IntervalReadTimeoutException;

public class SimpleReadTimeoutHandler
extends ChannelInboundHandlerAdapter {
    private volatile ScheduledFuture<?> timeoutTask;
    protected final long timeoutTime;
    protected long lastReadTime;
    protected boolean hasRead;

    public SimpleReadTimeoutHandler(int timeout) {
        this(timeout, TimeUnit.SECONDS);
    }

    public SimpleReadTimeoutHandler(long timeout, TimeUnit tu) {
        this.timeoutTime = tu.toMillis(timeout);
    }

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.initialize(ctx);
        super.channelActive(ctx);
    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        this.destroy();
        super.channelInactive(ctx);
    }

    public void channelRead(ChannelHandlerContext ctx, Object message) throws Exception {
        this.lastReadTime = System.currentTimeMillis();
        this.hasRead = true;
        ctx.fireChannelRead(message);
    }

    private void initialize(final ChannelHandlerContext ctx) {
        this.lastReadTime = System.currentTimeMillis();
        this.timeoutTask = ctx.executor().schedule(new Runnable(){

            @Override
            public void run() {
                if (ctx.channel().isOpen()) {
                    long untilTimeout = SimpleReadTimeoutHandler.this.timeoutTime - (System.currentTimeMillis() - SimpleReadTimeoutHandler.this.lastReadTime);
                    if (untilTimeout <= 0) {
                        try {
                            if (SimpleReadTimeoutHandler.this.hasRead) {
                                ctx.fireExceptionCaught((Throwable)IntervalReadTimeoutException.getInstance(SimpleReadTimeoutHandler.this.lastReadTime));
                            } else {
                                ctx.fireExceptionCaught((Throwable)FirstReadTimeoutException.getInstance(SimpleReadTimeoutHandler.this.lastReadTime));
                            }
                            ctx.close();
                        }
                        catch (Throwable e) {
                            ctx.fireExceptionCaught(e);
                        }
                    } else {
                        ctx.executor().schedule((Runnable)this, untilTimeout, TimeUnit.MILLISECONDS);
                    }
                }
            }
        }, this.timeoutTime, TimeUnit.MILLISECONDS);
    }

    private void destroy() {
        if (this.timeoutTask != null) {
            this.timeoutTask.cancel(false);
            this.timeoutTask = null;
        }
    }

}

