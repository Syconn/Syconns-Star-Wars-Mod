package mod.syconn.swm.util.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public class StreamCodecs {

    public static <T extends Enum<T>> StreamCodec<ByteBuf, T> enumCodec(Class<T> type) {
        return new StreamCodec<>() {
            @Override
            public T decode(ByteBuf byteBuf) {
                return type.getEnumConstants()[byteBuf.readInt()];
            }

            @Override
            public void encode(ByteBuf byteBuf, T type) {
                byteBuf.writeInt(type.ordinal());
            }
        };
    }

    public static final StreamCodec<ByteBuf, FriendlyByteBuf> FRIENDLY_BYTE_BUF = new StreamCodec<>() {
        @Override
        public FriendlyByteBuf decode(ByteBuf buf) {
            var readableBytes = buf.readInt();
            return new FriendlyByteBuf(Unpooled.wrappedBuffer(buf.readBytes(readableBytes)));
        }

        @Override
        public void encode(ByteBuf buf, FriendlyByteBuf friendlyBuf) {
            buf.writeInt(friendlyBuf.readableBytes());
            buf.writeBytes(friendlyBuf);
        }
    };
}
