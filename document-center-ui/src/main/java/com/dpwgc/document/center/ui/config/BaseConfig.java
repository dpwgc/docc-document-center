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

    @Value("${server.servlet.context-path}")
    private String contextPath;

    /**
     * spring boot项目启动后自动执行
     */
    @Override
    public void afterPropertiesSet() {

        System.out.println("\n==================== configuration ====================" +
                "\n<application> [name]: "+applicationName +
                "\n<datasource> [url]: "+datasourceUrl +
                "\n<elasticsearch> [url]: "+elasticsearchUrl+"    [index]: "+elasticsearchIndexName +
                "\n<cluster> [datacenter_id]: "+datacenterId+"    [worker_id]: "+workerId +
                "\n<monitor> [url]: http://localhost:"+serverPort+contextPath+"/monitor.html" +
                "\n<swagger api doc> [url]: http://localhost:"+serverPort+contextPath+"/doc.html" +
                "\n==================== elasticsearch index ====================");

        try {
            if (!esClient.existsIndex(elasticsearchIndexName)) {
                LogUtil.info("existsIndex","elasticsearch index does not exist","esClient");
                try {
                    esClient.createIndex(elasticsearchIndexName);
                    LogUtil.info("createIndex","elasticsearch index creation succeeded","esClient");
                } catch (Exception e) {
                    LogUtil.error("createIndex error",e.getMessage(),"esClient");
                }
            }
        } catch (Exception e) {
            LogUtil.error("existsIndex error",e.getMessage(),"esClient");
        }
    }
}
