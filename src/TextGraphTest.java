import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

//黑盒测试
public class TextGraphTest {
    private TextGraph.Graph graph;

    @Before
    public void setUp() {
        //初始化测试所需的图结构——根据设计测试用例时编写的测试文件创建
        graph = new TextGraph.Graph(true);

        //1-3,6,8的图结构
        graph.addEdge("a", "b", 1);
        graph.addEdge("b", "c", 1);
        graph.addEdge("c", "d", 1);

        //TC3的图结构
        graph.addEdge("f", "g", 1);
        graph.addEdge("g", "h", 1);
        graph.addEdge("c", "d", 1);

        //TC5,7的图结构
        graph.addEdge("j", "k1", 1);
        graph.addEdge("j", "k2", 1);
        graph.addEdge("k1", "l", 1);
        graph.addEdge("k2", "l", 1);

    }

    @Test
    public void testT1()
    {   //正常存在一个桥接词
        String result = TextGraph.generateNewText(graph, "a c d");
        assertEquals("a b c d", result);
    }

    @Test
    public void testT2()
    {   //输入为空
        String result = TextGraph.generateNewText(graph, "");
        assertEquals("", result);
    }

    @Test
    public void testT3()
    {   //输入为空
        String result = TextGraph.generateNewText(graph, "e");
        assertEquals("e", result);
    }

    @Test
    public void testT4()
    {   //存在单个桥接词，有特殊字符
        String result = TextGraph.generateNewText(graph, "f! h*");
        assertEquals("f g h", result);
    }

    @Test
    public void testT5()
    {   //有多个桥接词
        String result = TextGraph.generateNewText(graph, "j l");
        assertTrue(result.equals("j k1 l") || result.equals("j k2 l"));
    }

    @Test
    public void testT6()
    {   //无桥接词
        String result = TextGraph.generateNewText(graph, "a b c");
        assertEquals("a b c", result);
    }

    @Test
    public void testT7()
    {   //多个桥接词+特殊字符
        String result = TextGraph.generateNewText(graph, "j! l*");
        assertTrue(result.equals("j k1 l") || result.equals("j k2 l"));
    }

    @Test
    public void testT8()
    {   //判断大小写敏感
        String result = TextGraph.generateNewText(graph, "a! b*");
        assertEquals("a b", result);
    }

}