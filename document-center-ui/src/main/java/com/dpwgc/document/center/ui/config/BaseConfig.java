package com.dpwgc.document.center.ui.config;

import com.dpwgc.document.center.infrastructure.util.LogUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 基础配置读取
 */
@Configuration
public class BaseConfig implements InitializingBean {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${elasticsearch.scheme}")
    private String elasticsearchScheme;

    @Value("${elasticsearch.hostname}")
    private String elasticsearchHostname;

    @Value("${elasticsearch.port}")
    private String elasticsearchPort;

    @Value("${elasticsearch.indexName}")
    private String elasticsearchIndexName;

    @Value("${cluster.datacenterId}")
    private String datacenterId;

    @Value("${cluster.workerId}")
    private String workerId;

    /**
     * spring boot项目启动后自动执行
     */
    @Override
    public void afterPropertiesSet() {

        LogUtil.info("\n");

        LogUtil.info("==================== load configuration ====================");

        LogUtil.info("<application> [name]: "+applicationName);

        LogUtil.info("<datasource> [url]: "+datasourceUrl);

        LogUtil.info("<elasticsearch> [url]: "+elasticsearchScheme+"://"+elasticsearchHostname+":"+elasticsearchPort+" | [index]: "+elasticsearchIndexName);

        LogUtil.info("<cluster> [datacenter id]: "+datacenterId+" | [worker id]: "+workerId);

        LogUtil.info("==================== load successful ====================");
    }
}
