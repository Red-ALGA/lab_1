import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

//白盒测试
public class TextGraphTest {
    private TextGraph.Graph graph;

    @Before
    public void setUp() {
        //构建测试图用例
        graph = new TextGraph.Graph(true);
        graph.addEdge("hot", "warm", 1);
        graph.addEdge("warm", "dog", 1);
        graph.addEdge("hot", "spicy", 1);
        graph.addEdge("spicy", "dog", 1);
        graph.addEdge("apple", "boy", 1);
        graph.addEdge("boy", "cat", 1);
        graph.addEdge("hello", "world", 1);
    }

    @Test
    public void testWord1NotInGraph()
    {   //第一个输入不在有向图中
        String result = TextGraph.queryBridgeWords(graph, "nonexist", "hello");
        assertEquals("No nonexist in the graph!", result);
    }

    @Test
    public void testWord2NotInGraph()
    {   //第二个输入不在有向图中
        String result = TextGraph.queryBridgeWords(graph, "hello", "nonexist");
        assertEquals("No nonexist in the graph!", result);
    }

    @Test
    public void testNoBridgeWords()
    {   //两个单词之间没有桥接词
        String result = TextGraph.queryBridgeWords(graph, "hello", "world");
        assertEquals("No bridge words from hello to world!", result);
    }

    @Test
    public void testSingleBridgeWord()
    {   //两个单词只有一个桥接词

        String result = TextGraph.queryBridgeWords(graph, "apple", "cat");
        assertEquals("The bridge word from apple to cat is: boy", result);
    }

    @Test
    public void testMultipleBridgeWords()
    {   //两个单词有多个桥接词
        String result = TextGraph.queryBridgeWords(graph, "hot", "dog");

        assertTrue(result.startsWith("The bridge words from hot to dog are: "));
        assertTrue(result.contains("warm"));
        assertTrue(result.contains("spicy"));
    }
}