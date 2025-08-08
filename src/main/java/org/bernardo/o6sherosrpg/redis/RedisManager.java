package org.bernardo.o6sherosrpg.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.UUID;

public class RedisManager {

    private JedisPool pool;

    public void connect() {
        pool = new JedisPool("localhost", 6379);
    }

    public void close() {
        if (pool != null) pool.close();
    }

    public void adicionarJogadorFilaTime(UUID uuid) {
        try (Jedis jedis = pool.getResource()) {
            jedis.rpush("fila:times", uuid.toString());
        }
    }

    public List<String> buscarTime(int max) {
        try (Jedis jedis = pool.getResource()) {
            List<String> jogadores = jedis.lrange("fila:times", 0, max - 1);
            jedis.ltrim("fila:times", max, -1);
            return jogadores;
        }
    }

    public long getTamanhoFilaTimes() {
        try (Jedis jedis = pool.getResource()) {
            return jedis.llen("fila:times");
        }
    }

}
