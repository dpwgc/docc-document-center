package com.dpwgc.document.center.ui.config;

import com.dpwgc.document.center.infrastructure.component.ESClient;
import com.dpwgc.document.center.infrastructure.util.LogUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 基础配置读取
 */
@Configuration
public class BaseConfig implements InitializingBean {

    @Resource
    ESClient esClient;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private String serverPort;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${elasticsearch.url}")
    private String elasticsearchUrl;

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

        LogUtil.info("==================== configuration ====================");

        LogUtil.info("<application> [name]: "+applicationName);

        LogUtil.info("<datasource> [url]: "+datasourceUrl);

        LogUtil.info("<elasticsearch> [url]: "+elasticsearchUrl+"    [index]: "+elasticsearchIndexName);

        LogUtil.info("<cluster> [datacenter_id]: "+datacenterId+"    [worker_id]: "+workerId);

        LogUtil.info("<monitor> [url]: http://localhost:"+serverPort+"/monitor.html");

        LogUtil.info("==================== elasticsearch index ====================");

        if (!esClient.existsIndex(elasticsearchIndexName)) {
            LogUtil.info("elasticsearch index does not exist");
            if (esClient.createIndex(elasticsearchIndexName)) {
                LogUtil.info("elasticsearch index creation succeeded");
            }
        }
    }
}
