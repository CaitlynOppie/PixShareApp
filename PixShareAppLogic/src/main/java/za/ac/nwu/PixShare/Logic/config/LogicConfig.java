package za.ac.nwu.PixShare.Logic.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import za.ac.nwu.PixShare.Translator.config.TranslatorConfig;

@Import({TranslatorConfig.class})
@Configuration
@ComponentScan(basePackages = {
        "za.ac.nwu.PixShare.Logic"
})
public class LogicConfig {
}
