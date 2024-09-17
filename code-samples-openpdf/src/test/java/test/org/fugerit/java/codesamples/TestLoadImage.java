package test.org.fugerit.java.codesamples;

import com.lowagie.text.Image;
import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.codesamples.openpdf.LoadImage;
import org.fugerit.java.core.io.StreamIO;
import org.fugerit.java.core.lang.helpers.ClassHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;

/**
 * On logo1.png, an exception is thrown (while Image.getInstance() on IText2, using custom code, it works) :
 *

 [main] WARN test.org.fugerit.java.codesamples.TestLoadImage - Errore ImageLoader.getPngImage() javax.imageio.IIOException: Error reading PNG image data
 javax.imageio.IIOException: Error reading PNG image data
 at java.desktop/com.sun.imageio.plugins.png.PNGImageReader.readImage(PNGImageReader.java:1527)
 at java.desktop/com.sun.imageio.plugins.png.PNGImageReader.read(PNGImageReader.java:1844)
 at java.desktop/javax.imageio.ImageIO.read(ImageIO.java:1466)
 at java.desktop/javax.imageio.ImageIO.read(ImageIO.java:1363)
 at test.org.fugerit.java.codesamples.TestLoadImage.testImageIO(TestLoadImage.java:40)
 at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103)
 at java.base/java.lang.reflect.Method.invoke(Method.java:580)
 at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
 at org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
 at org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
 at org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
 at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestableMethod(TimeoutExtension.java:147)
 at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestMethod(TimeoutExtension.java:86)
 at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker$ReflectiveInterceptorCall.lambda$ofVoidMethod$0(InterceptingExecutableInvoker.java:103)
 at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.lambda$invoke$0(InterceptingExecutableInvoker.java:93)
 at org.junit.jupiter.engine.execution.InvocationInterceptorChain$InterceptedInvocation.proceed(InvocationInterceptorChain.java:106)
 at org.junit.jupiter.engine.execution.InvocationInterceptorChain.proceed(InvocationInterceptorChain.java:64)
 at org.junit.jupiter.engine.execution.InvocationInterceptorChain.chainAndInvoke(InvocationInterceptorChain.java:45)
 at org.junit.jupiter.engine.execution.InvocationInterceptorChain.invoke(InvocationInterceptorChain.java:37)
 at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:92)
 at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:86)
 at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeTestMethod$7(TestMethodTestDescriptor.java:218)
 at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
 at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeTestMethod(TestMethodTestDescriptor.java:214)
 at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:139)
 at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:69)
 at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:151)
 at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
 at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
 at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
 at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
 at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
 at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
 at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
 at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
 at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
 at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:155)
 at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
 at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
 at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
 at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
 at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
 at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
 at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
 at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
 at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
 at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:155)
 at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
 at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
 at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
 at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
 at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
 at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
 at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
 at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
 at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
 at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
 at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:198)
 at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:169)
 at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:93)
 at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:58)
 at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:141)
 at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:57)
 at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:103)
 at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:85)
 at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
 at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:63)
 at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:57)
 at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
 at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
 at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
 at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:232)
 at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
 Caused by: java.util.zip.ZipException: invalid distance too far back
 at java.base/java.util.zip.InflaterInputStream.read(InflaterInputStream.java:181)
 at java.base/java.io.BufferedInputStream.fill(BufferedInputStream.java:291)
 at java.base/java.io.BufferedInputStream.implRead(BufferedInputStream.java:325)
 at java.base/java.io.BufferedInputStream.read(BufferedInputStream.java:312)
 at java.base/java.io.FilterInputStream.read(FilterInputStream.java:71)
 at java.desktop/com.sun.imageio.plugins.png.PNGImageReader.decodePass(PNGImageReader.java:1235)
 at java.desktop/com.sun.imageio.plugins.png.PNGImageReader.decodeImage(PNGImageReader.java:1378)
 at java.desktop/com.sun.imageio.plugins.png.PNGImageReader.readImage(PNGImageReader.java:1518)
 ... 72 more

 *
 */
@Slf4j
class TestLoadImage {

    private static final String[] IMAGES = { "logo1.png", "logo2.png" };

    @Test
    void testFromBytes() {
        for ( String current : IMAGES ) {
            String currentTest = "test_image/"+current;
            try (InputStream is = ClassHelper.loadFromDefaultClassLoader( currentTest ) ) {
                log.info( "currentTest {} - {}", LoadImage.class.getName(), currentTest );
                Image img = LoadImage.fromBytes( StreamIO.readBytes( is ) );
                Assertions.assertNotNull( img );
            } catch (Exception e) {
                log.warn( "Errore "+e, e );
            }
        }
    }

    @Test
    void testImageIO() {
        for ( String current : IMAGES ) {
            String currentTest = "test_image/"+current;
            try (InputStream is = ClassHelper.loadFromDefaultClassLoader( currentTest ) ) {
                log.info( "currentTest ImageLoader.getPngImage() {}", currentTest );
                BufferedImage bufferedImage = ImageIO.read(is);
                Image img =  Image.getInstance(bufferedImage, null, false);
                Assertions.assertNotNull( img );
            } catch (Exception e) {
                log.warn( "Errore ImageLoader.getPngImage() "+e, e );
            }
        }
    }

}
