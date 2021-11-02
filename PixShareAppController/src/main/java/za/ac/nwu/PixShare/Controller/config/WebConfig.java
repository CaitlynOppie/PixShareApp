package za.ac.nwu.PixShare.Controller.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import za.ac.nwu.PixShare.Service.config.ServiceConfig;

@Import({ServiceConfig.class})
@Configuration
@ComponentScan(basePackages = {
        "za.ac.nwu.PixShare.Controller.controller"
})
public class WebConfig {
}
