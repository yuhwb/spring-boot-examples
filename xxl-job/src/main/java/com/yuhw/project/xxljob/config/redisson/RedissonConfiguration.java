package com.yuhw.project.xxljob.config.redisson;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties(RedissonProperties.class)
@Slf4j
public class RedissonConfiguration {
    @Autowired
    private RedissonProperties redissonProperties;

    @Bean
    @ConditionalOnMissingBean
    public Config config() throws IOException {
        String path = redissonProperties.getConfigFile().getPath();
        if (StringUtils.isEmpty(path)){
            throw new RuntimeException("please offer the config file by json/yaml");
        }
        if (path.endsWith(".json")) {
            return Config.fromJSON(ResourceUtils.getFile(path));
        }
        if (path.endsWith(".yaml")){
            return Config.fromYAML(ResourceUtils.getFile(path));
        }
        throw new RuntimeException("please offer the config file by json/yaml");
    }

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean
    public RedissonClient redissonClient(Config config) throws IOException{
        log.info("create RedissonClient, config is : {}" , config.toJSON());
        return Redisson.create(config);
    }
}
