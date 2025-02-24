package org.dacss.projectinitai.system;

import org.dacss.projectinitai.system.options.MemoryCap;
import org.testng.annotations.Test;
import reactor.core.publisher.Flux;

import static org.testng.Assert.*;

/**
 * <h1>{@link MemoryCapTest}</h1>
 * Methods under test:
 * <ul>
 *     <li>{@link MemoryCap#getTotalMemorySize()}</li>
 *     <li>{@link MemoryCap#getAllocatedMemorySize()}</li>
 *     <li>{@link MemoryCap#setProjectMaxMemory()}</li>
 *     <li>{@link MemoryCap#getMemoryStats()}</li>
 *     <li>{@link MemoryCap#checkMemoryUsage()}</li>
 *     <li>{@link MemoryCap#startMemoryMonitoring()}</li>
 * </ul>
 */
public class MemoryCapTest {

    @Test
    public void testGetTotalMemorySize() {
        long totalMemorySize = MemoryCap.getTotalMemorySize();
        System.out.println("Total Memory Size: " + totalMemorySize);
        assertTrue(totalMemorySize > 0, "Total memory size should be greater than 0");
    }

    @Test
    public void testGetAllocatedMemorySize() {
        long allocatedMemorySize = MemoryCap.getAllocatedMemorySize();
        System.out.println("Allocated Memory Size: " + allocatedMemorySize);
        assertTrue(allocatedMemorySize >= 0, "Allocated memory size should be non-negative");
    }

    @Test
    public void testSetProjectMaxMemory() {
        MemoryCap.setProjectMaxMemory();
        long totalMemorySize = MemoryCap.getTotalMemorySize();
        long allocatedMemorySize = MemoryCap.getAllocatedMemorySize();
        long availableMemory = totalMemorySize - allocatedMemorySize;
        long expectedMemoryCap = (long) (availableMemory * 0.9);
        System.out.println("Expected Memory Cap: " + expectedMemoryCap);
        System.out.println("Actual Memory Cap: " + MemoryCap.memoryCap);
        assertEquals(MemoryCap.memoryCap, expectedMemoryCap, "Memory cap should be 90% of available memory");
    }

    @Test
    public void testGetMemoryStats() {
        MemoryCap.setProjectMaxMemory();
        Flux<Object> memoryStats = MemoryCap.getMemoryStats();
        memoryStats.subscribe(stat -> System.out.println("Memory Stat: " + stat));
        assertNotNull(memoryStats, "Memory stats should not be null");
    }

    @Test
    public void testCheckMemoryUsage() {
        MemoryCap.setProjectMaxMemory();
        Flux<Object> memoryUsageCheck = MemoryCap.checkMemoryUsage();
        memoryUsageCheck.subscribe(check -> System.out.println("Memory Usage Check: " + check));
        assertNotNull(memoryUsageCheck, "Memory usage check should not be null");
    }

    @Test
    public void testStartMemoryMonitoring() {
        MemoryCap.setProjectMaxMemory();
        MemoryCap.startMemoryMonitoring();
        System.out.println("Mock Memory monitoring started.");
        // Since this method starts a monitoring process, we can't assert its behavior directly.
        // Instead, we ensure no exceptions are thrown during the start.
    }
}
