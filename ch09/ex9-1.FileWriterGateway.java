// 리스트 9.1 메서드 호출을 메시지로 변환하는 메시지 게이트웨이 인터페이스

package sia5;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel="textInChanne")
public interface FileWriterGateway {
	
	void writeToFile(
		@Header(FileHeaders.FILENAME) String filename,
		String data);
}
