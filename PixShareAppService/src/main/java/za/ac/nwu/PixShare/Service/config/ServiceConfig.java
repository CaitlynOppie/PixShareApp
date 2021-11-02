package za.ac.nwu.PixShare.Service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import za.ac.nwu.PixShare.Repo.config.RepoConfig;

@Import({RepoConfig.class})
@Configuration
@ComponentScan(basePackages = {
        "za.ac.nwu.PixShare.Service"
})
public class ServiceConfig {


}
