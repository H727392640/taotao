import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/7
 * Time: 13:08
 * Description: No Description
 */
public class JedisTest {

    @Test
    public void testJedisSingle() {
        Jedis jedis = new Jedis("192.168.180.129", 6379);
        jedis.set("key1", "jedis test");
        String str = jedis.get("key1");
        System.out.println(str);
        jedis.close();
    }

    @Test
    public void testJedisPool() {
        JedisPool jedisPool = new JedisPool("192.168.180.129", 6379);

        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis.get("key1"));
        jedis.close();
    }

    @Test
    public void testJedisCluster() {
        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.180.129", 7001));
        nodes.add(new HostAndPort("192.168.180.129", 7002));
        nodes.add(new HostAndPort("192.168.180.129", 7003));
        nodes.add(new HostAndPort("192.168.180.129", 7004));
        nodes.add(new HostAndPort("192.168.180.129", 7005));
        nodes.add(new HostAndPort("192.168.180.129", 7006));
        JedisCluster cluster = new JedisCluster(nodes);
        cluster.set("key1", "1000");
        System.out.println(cluster.get("key1"));
        cluster.close();
    }

}
