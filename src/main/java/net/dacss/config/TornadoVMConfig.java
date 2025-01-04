package net.dacss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.ac.manchester.tornado.api.TornadoVM;

@Configuration
public class TornadoVMConfig {

    @Bean
    public TornadoVM tornadoVM() {
        TornadoVM tornadoVM = new TornadoVM();
        // Configure TornadoVM for hardware acceleration
        tornadoVM.setHardwareAcceleration(true);
        return tornadoVM;
    }
}
