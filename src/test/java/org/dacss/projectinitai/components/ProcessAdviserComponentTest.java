//package org.dacss.projectinitai.components;
//
//import org.dacss.projectinitai.advisers.processors.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class ProcessAdviserComponentTest {
//
//    private ProcessAdviserComp processAdviserComponent;
//
//    @BeforeEach
//    void setUp() {
//        processAdviserComponent = new ProcessAdviserComp();
//    }
//
//    @Test
//    void testProcessStringInput() {
//        String input = "Test Input";
//        String expectedOutput = "Processed Input";
//
//        StringProcessingAdviserIface mockProcessor = mock(StringProcessingAdviserIface.class);
//        when(mockProcessor.processString(input)).thenReturn(expectedOutput);
//
//        processAdviserComponent.registerProcessors(mockProcessor);
//
//        Object result = processAdviserComponent.process(input);
//        assertEquals(expectedOutput, result);
//    }
//
//    @Test
//    void testProcessByteInput() {
//        byte[] input = "Test Input".getBytes();
//        byte[] expectedOutput = "Processed Input".getBytes();
//
//        ByteProcessingAdviserIface mockProcessor = mock(ByteProcessingAdviserIface.class);
//        when(mockProcessor.processBytes(input)).thenReturn(expectedOutput);
//
//        processAdviserComponent.registerProcessors(mockProcessor);
//
//        Object result = processAdviserComponent.process(input);
//        assertEquals(expectedOutput, result);
//    }
//}
