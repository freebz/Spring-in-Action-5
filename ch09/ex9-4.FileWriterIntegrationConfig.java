// 리스트 9.4 스프링 통합의 자바 DSL 구성을 사용해서 통합 플로우 정의하기

package sia5;

import java.io.File;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;

@Configuration
public class FileWriterIntegrationConfig {
	
	@Bean
	public IntegrationFlow fileWriterFlow() {
		return IntegrationFlows
			.from(MessageChannels.direct("textInChannel"))
			.<String, String>transform(t -> t.toUpperCase())
			.handle(Files
				.outboundAdapter(new File("/tmp/sia5/files"))
				.fileExistsMode(FileExistsMode.APPEND)
				.appendNewLine(true))
			.get();
	}
}
