package lsieun.jmx.config;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

public class SystemConfigManagement {
    private static final int DEFAULT_NO_THREADS=10;
    private static final String DEFAULT_SCHEMA="default";

    public static void main(String[] args) {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        SystemConfig mBean = new SystemConfig(DEFAULT_NO_THREADS, DEFAULT_SCHEMA);

        try {
            ObjectName name = new ObjectName("lsieun.jmx.config:type=SystemConfig");
            server.registerMBean(mBean, name);
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (NotCompliantMBeanException e) {
            e.printStackTrace();
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        } catch (MBeanRegistrationException e) {
            e.printStackTrace();
        }

        do {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread Count="+mBean.getThreadCount()+":::Schema Name="+mBean.getSchemaName());
        } while (mBean.getThreadCount() != 0);
    }
}
