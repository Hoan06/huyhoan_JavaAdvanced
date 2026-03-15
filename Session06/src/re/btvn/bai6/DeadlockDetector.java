package re.btvn.bai6;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class DeadlockDetector implements Runnable {

    @Override
    public void run() {

        System.out.println("Đang quét deadlock...");

        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        long[] ids = bean.findDeadlockedThreads();

        if (ids == null) {
            System.out.println("Không phát hiện deadlock.");
            return;
        }

        ThreadInfo[] infos = bean.getThreadInfo(ids);

        for (ThreadInfo info : infos) {
            System.out.println(info.getThreadName() + " bị deadlock.");
        }
    }
}