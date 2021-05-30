package companies.yandex.yandex.contest.ETask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ETask {

    private final static Map<Integer, Integer> userCountConnectionsMap = new HashMap<>();
    private final static List<UserRequest> connectionsPool = new LinkedList<>();
    private static int userLimit;
    private static int serviceLimit;
    private static int durationMs;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String[] paramsString = r.readLine().split(" ");
        userLimit = Integer.parseInt(paramsString[0]);
        serviceLimit = Integer.parseInt(paramsString[1]);
        durationMs = Integer.parseInt(paramsString[2]);

        for (; ; ) {
            String line = r.readLine();
            if (line != null && line.length() == 2 && Integer.parseInt(line) == -1) {
                break;
            }
            if (line != null && line.length() > 0) {
                String[] lineParams = line.split(" ");
                Integer requestTime = Integer.parseInt(lineParams[0]);
                Integer userId = Integer.parseInt(lineParams[1]);
                UserRequest userRequest = new UserRequest(requestTime, userId);
                System.out.println(processRequest(userRequest));
                System.out.flush();
                cleanPool(userRequest);
            }
        }
    }

    private static int processRequest(UserRequest request) {
        Integer currentUserConnections = userCountConnectionsMap.get(request.getUserId());
        if (currentUserConnections != null) {
            if (currentUserConnections >= userLimit) {
                return 429;
            } else if (connectionsPool.size() >= serviceLimit) {
                return 503;
            } else {
                userCountConnectionsMap.put(request.getUserId(), ++currentUserConnections);
                connectionsPool.add(request);
            }
        } else {
            if (connectionsPool.size() >= serviceLimit) {
                return 503;
            } else {
                userCountConnectionsMap.put(request.getUserId(), 1);
                connectionsPool.add(request);
            }
        }
        return 200;
    }

    private static void cleanPool(UserRequest request) {
            connectionsPool.removeIf(connection -> {
                if (Math.abs(request.getRequestTime() - connection.getRequestTime()) >= durationMs) {
                    Integer userConnectionsCount = userCountConnectionsMap.get(connection.getUserId());
                    if (userConnectionsCount != null && userConnectionsCount > 0) {
                        userCountConnectionsMap.put(connection.getUserId(), --userConnectionsCount);
                    }
//                    else if (userConnectionsCount != null && userConnectionsCount == 0) {
//                        userCountConnectionsMap.remove(connection.getUserId(), 0);
//                    }
                    return true;
                }
                return false;
            });

    }

    private static class UserRequest {
        private final Integer requestTime;
        private final Integer userId;

        public UserRequest(Integer requestTime, Integer userId) {
            this.requestTime = requestTime;
            this.userId = userId;
        }

        public Integer getRequestTime() {
            return requestTime;
        }

        public Integer getUserId() {
            return userId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UserRequest that = (UserRequest) o;
            return requestTime.equals(that.requestTime) && userId.equals(that.userId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(requestTime, userId);
        }
    }

}
