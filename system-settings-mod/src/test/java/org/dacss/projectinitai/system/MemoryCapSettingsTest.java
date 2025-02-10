package org.dacss.projectinitai.system;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reactor.core.publisher.Flux;

import static org.testng.Assert.*;

/**
 * <h1>{@link MemoryCapSettingsTest}</h1>
 * Methods under test:
 * <ul>
 *     <li>{@link MemoryCapSettings#getTotalMemorySize()}</li>
 *     <li>{@link MemoryCapSettings#getAllocatedMemorySize()}</li>
 *     <li>{@link MemoryCapSettings#setProjectMaxMemory()}</li>
 *     <li>{@link MemoryCapSettings#getMemoryStats()}</li>
 *     <li>{@link MemoryCapSettings#checkMemoryUsage()}</li>
 *     <li>{@link MemoryCapSettings#startMemoryMonitoring()}</li>
 * </ul>
 */
public class MemoryCapSettingsTest {

    @Test
    public void testGetTotalMemorySize() {
        long totalMemorySize = MemoryCapSettings.getTotalMemorySize();
        System.out.println("Total Memory Size: " + totalMemorySize);
        assertTrue(totalMemorySize > 0, "Total memory size should be greater than 0");
    }

    @Test
    public void testGetAllocatedMemorySize() {
        long allocatedMemorySize = MemoryCapSettings.getAllocatedMemorySize();
        System.out.println("Allocated Memory Size: " + allocatedMemorySize);
        assertTrue(allocatedMemorySize >= 0, "Allocated memory size should be non-negative");
    }

    @Test
    public void testSetProjectMaxMemory() {
        MemoryCapSettings.setProjectMaxMemory();
        long totalMemorySize = MemoryCapSettings.getTotalMemorySize();
        long allocatedMemorySize = MemoryCapSettings.getAllocatedMemorySize();
        long availableMemory = totalMemorySize - allocatedMemorySize;
        long expectedMemoryCap = (long) (availableMemory * 0.9);
        System.out.println("Expected Memory Cap: " + expectedMemoryCap);
        System.out.println("Actual Memory Cap: " + MemoryCapSettings.memoryCap);
        assertEquals(MemoryCapSettings.memoryCap, expectedMemoryCap, "Memory cap should be 90% of available memory");
    }

    @Test
    public void testGetMemoryStats() {
        MemoryCapSettings.setProjectMaxMemory();
        Flux<Object> memoryStats = MemoryCapSettings.getMemoryStats();
        memoryStats.subscribe(stat -> System.out.println("Memory Stat: " + stat));
        assertNotNull(memoryStats, "Memory stats should not be null");
    }

    @Test
    public void testCheckMemoryUsage() {
        MemoryCapSettings.setProjectMaxMemory();
        Flux<Object> memoryUsageCheck = MemoryCapSettings.checkMemoryUsage();
        memoryUsageCheck.subscribe(check -> System.out.println("Memory Usage Check: " + check));
        assertNotNull(memoryUsageCheck, "Memory usage check should not be null");
    }

    @Test
    public void testStartMemoryMonitoring() {
        MemoryCapSettings.setProjectMaxMemory();
        MemoryCapSettings.startMemoryMonitoring();
        System.out.println("Mock Memory monitoring started.");
        // Since this method starts a monitoring process, we can't assert its behavior directly.
        // Instead, we ensure no exceptions are thrown during the start.
    }
}
