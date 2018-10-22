package alpdev.nettyModifyDiscardServer;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
     * Handles a server-side channel.
     */
    public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)




/* It's default implementation
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
            // Discard the received data silently.
            ((ByteBuf) msg).release(); // (3)
        }
*/

// It's modified implementation
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            //Here we print incoming message how string and send it back
            ByteBuf in = (ByteBuf) msg;
            System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));


            ctx.write(msg); // (1)
            ctx.flush(); // (2)

//Here we print incoming message char by char

/*         try {
                while (in.isReadable()) { // (1)
                    System.out.print((char) in.readByte());
                    System.out.flush();
                }
            } finally {
                ReferenceCountUtil.release(msg); // (2)
            }
*/
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
            // Close the connection when an exception is raised.
            cause.printStackTrace();
            ctx.close();
        }
    }

