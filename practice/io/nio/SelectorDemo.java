package com.sjh.thinkinginjava.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorDemo {
    public static void main(String[] args) throws IOException {
        //创建Selector
        Selector selector = Selector.open();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);//设置为非阻塞模式
        channel.socket().bind(new InetSocketAddress(80));//绑定端口
        /**
         *  Connect(SelectionKey.OP_CONNECT) : 连接就绪
            Accept(SelectionKey.OP_ACCEPT) : 接收就绪
            Read(SelectionKey.OP_READ) : 读就绪
            Write(SelectionKey.OP_WRITE) : 写就绪
         */
        channel.register(selector, SelectionKey.OP_ACCEPT);//注册监听事件
        while (true) {
            Set<SelectionKey> selectionKeys =  selector.selectedKeys();//取得所有key集合
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if((selectionKey.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();//接收到服务端的请求
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    /**
                     *  注意每次迭代末尾的keyIterator.remove()调用。Selector不会自己从已选择键集中移除SelectionKey实例。
                     *  必须在处理完通道时自己移除。下次该通道变成就绪时，Selector会再次将其放入已选择键集中。
                     */
                    iterator.remove();
                } else if ((selectionKey.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                    while (true) {
                        byteBuffer.clear();
                        int n = socketChannel.read(byteBuffer);//读取数据
                        if(n < 0) {
                            break;
                        }
                        byteBuffer.flip();
                    }
                    iterator.remove();
                }
            }
        }
    }
}
