// 리스트 14.10 Gogs 알림 추출기 구현 클래스

package tacos.gogs;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.cloud.config.monitor.PropertyPathNotification;
import org.springframework.cloud.config.monitor.PropertyPathNotificationExtractor;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
public class GogsPropertyPathNotificationExtractor
		implements PropertyPathNotificationExtractor {

	@Override
	public PropertyPathNotification extract(
			MultiValueMap<String, String> headers,
			Map<String, Object> request) {
		if ("push".equals(headers.getFirst("X-Gogs-Event"))) {
			if (request.get("commits") instanceof Collection) {
				Set<String> paths = new HashSet<>();
				@SuppressWarnings("unchecked")
				Collection<Map<String, Object>> commits =
						(Collection<Map<String, Object>>) request.get("commits");
				for (Map<String, Object> commit : commits) {
					addAllPaths(paths, commit, "added");
					addAllPaths(paths, commit, "removed");
					addAllPaths(paths, commit, "modified");
				}
				if (!paths.isEmpty()) {
					return new PropertyPathNotification(
							paths.toArray(new String[0]));
				}
			}
		}
		return null;
	}
	
	private void addAllPaths(Set<String> paths,
								Map<String, Object> commit,
								String name) {
		@SuppressWarnings("unchecked")
		Collection<String> files =
				(Collection<String>) commit.get(name);
		if (files != null) {
			paths.addAll(files);
		}
	}
}
