package io.quarkiverse.twitter4j.deployment;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.ExtensionSslNativeSupportBuildItem;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;
import io.quarkus.deployment.builditem.nativeimage.RuntimeInitializedClassBuildItem;

class Twitter4jProcessor {

    private static final String FEATURE = "twitter4j";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    /**
     * Twitter needs HTTPS
     */
    @BuildStep
    ExtensionSslNativeSupportBuildItem activateSslNativeSupport() {
        return new ExtensionSslNativeSupportBuildItem(FEATURE);
    }

    @BuildStep
    ReflectiveClassBuildItem reflectiveClasses() {
        return ReflectiveClassBuildItem.builder(
                "twitter4j.TwitterImpl",
                "twitter4j.DispatcherImpl",
                "twitter4j.HttpClientImpl")
                .build();
    }

    @BuildStep
    RuntimeInitializedClassBuildItem runtimeInitialized() {
        // Twitter4j uses JMX in this class
        return new RuntimeInitializedClassBuildItem("twitter4j.TwitterAPIMonitor");
    }

}
