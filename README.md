A minimal example repo to try to sort out issues raised [here](https://goo.gl/w5Tbq0).

To test:
```
jsirois@gill ~/dev/pantsbuild/leo_liang (master) $ ./pants binary ::

10:44:17 00:00 [main]
               See a report at: http://localhost:32983/run/pants_run_2016_07_12_10_44_17_669_a7e5dd5d87f34060a8355c8d3c431aa6
10:44:17 00:00   [setup]
10:44:17 00:00     [parse]
               Executing tasks in goals: bootstrap -> imports -> unpack-jars -> deferred-sources -> gen -> jvm-platform-validate -> resolve -> resources -> compile -> binary
10:44:17 00:00   [bootstrap]
10:44:17 00:00     [substitute-aliased-targets]
10:44:17 00:00     [jar-dependency-management]
10:44:17 00:00     [bootstrap-jvm-tools]
10:44:17 00:00     [provide-tools-jar]
10:44:17 00:00   [imports]
10:44:17 00:00     [ivy-imports]
10:44:17 00:00   [unpack-jars]
10:44:17 00:00     [unpack-jars]
10:44:17 00:00   [deferred-sources]
10:44:17 00:00     [deferred-sources]
10:44:17 00:00   [gen]
10:44:17 00:00     [thrift]
10:44:17 00:00     [protoc]
10:44:17 00:00     [antlr]
10:44:17 00:00     [ragel]
10:44:17 00:00     [jaxb]
10:44:17 00:00     [wire]
10:44:17 00:00   [jvm-platform-validate]
10:44:17 00:00     [jvm-platform-validate]WARN] No default jvm platform is defined.

10:44:17 00:00       [cache] 
                   No cached artifacts for 1 target.
                   Invalidated 1 target.
10:44:17 00:00   [resolve]
10:44:17 00:00     [ivy]
10:44:17 00:00   [resources]
10:44:17 00:00     [prepare]
10:44:17 00:00     [services]
10:44:17 00:00   [compile]
10:44:17 00:00     [compile-jvm-prep-command]
10:44:17 00:00       [jvm_prep_command]
10:44:17 00:00     [compile-prep-command]
10:44:17 00:00     [compile]
10:44:17 00:00     [zinc]
10:44:17 00:00       [cache] 
                   No cached artifacts for 1 target.
                   Invalidated 1 target.
10:44:17 00:00       [isolation-zinc-pool-bootstrap] 
                   [1/1] Compiling 1 zinc source in 1 target (src/java/hwc:main-bin).
10:44:17 00:00       [compile]
                     
10:44:17 00:00         [zinc]
                       [info] Compiling 1 Java source to /home/jsirois/dev/pantsbuild/leo_liang/.pants.d/compile/zinc/252d64521cf9/src.java.hwc.main-bin/current/classes...
                       [info] Compile success at Jul 12, 2016 10:44:18 AM [0.075s]
                       
10:44:18 00:01       [unused-check]
                     
                     Target src/java/hwc:main-bin had unused dependencies:
                       '3rdparty:slf4j-simple',
                       '3rdparty:thrift-0.9.2',
                       'src/thrift/hws:hello-world-service-java',
                     (If you're seeing this message in error, you might need to change the `scope` of the dependencies.)
                     
10:44:18 00:01     [jvm-dep-check]
10:44:18 00:01   [binary]
10:44:18 00:01     [binary-jvm-prep-command]
10:44:18 00:01       [jvm_prep_command]
10:44:18 00:01     [binary-prep-command]
10:44:18 00:01     [python-binary-create]
10:44:18 00:01     [jvm]
                   creating dist/hello-world-client.jar
10:44:18 00:01       [create-monolithic-jar]
10:44:18 00:01         [add-internal-classes]
10:44:18 00:01         [add-dependency-jars]
10:44:18 00:01         [jar-tool]
10:44:18 00:01     [dup]
10:44:18 00:01   [complete]
               SUCCESS
```

And you can then inspect the monolithic jar (see the inclusion of
libthrift, its deps, the generated thrift code and the main class):
```
$ zipinfo dist/hello-world-client.jar 
Archive:  dist/hello-world-client.jar
Zip file size: 1270670 bytes, number of entries: 1073
-rw----     2.0 fat        0 bX defN 16-Jul-12 10:44 META-INF/
-rw----     2.0 fat      108 bl defN 16-Jul-12 10:44 META-INF/MANIFEST.MF
-rw----     2.0 fat        0 bl defN 16-Jul-12 10:44 org/
-rw----     2.0 fat        0 bl defN 16-Jul-12 10:44 org/apache/
-rw----     2.0 fat        0 bl defN 16-Jul-12 10:44 org/apache/http/
-rw----     2.0 fat        0 bl defN 16-Jul-12 10:44 org/apache/http/conn/
-rw----     2.0 fat        0 bl defN 16-Jul-12 10:44 org/apache/http/conn/params/
-rw----     2.0 fat      679 b- defN 13-Apr-19 18:19 org/apache/http/conn/params/ConnManagerParams$1.class
...
-rw----     1.0 fat      277 b- stor 16-Jul-12 10:44 HelloWorldClient.class
...
-rw----     2.0 fat        0 bl defN 16-Jul-12 10:44 org/apache/thrift/
-rw----     2.0 fat      134 b- defN 14-Nov-05 03:47 org/apache/thrift/TEnum.class
...
-rw----     2.0 fat        0 bl defN 16-Jul-12 10:44 org/slf4j/
-rw----     2.0 fat        0 bl defN 16-Jul-12 10:44 org/slf4j/impl/
-rw----     2.0 fat     1285 b- defN 16-Apr-04 20:20 org/slf4j/impl/SimpleLoggerFactory.class
...
-rw----     2.0 fat        0 bl defN 16-Jul-12 10:44 hws/
-rw----     1.0 fat     2792 b- stor 16-Jul-12 10:38 hws/HelloWorldService$helloWorld_result$_Fields.class
...
-rw----     2.0 fat        0 bl defN 16-Jul-12 10:44 org/apache/commons/
-rw----     2.0 fat        0 bl defN 16-Jul-12 10:44 org/apache/commons/codec/
-rw----     2.0 fat        0 bl defN 16-Jul-12 10:44 org/apache/commons/codec/binary/
-rw----     2.0 fat     8314 b- defN 11-Dec-02 21:37 org/apache/commons/codec/binary/Base64.class
...
-rw----     2.0 fat        0 bl defN 16-Jul-12 10:44 org/slf4j/spi/
-rw----     2.0 fat      455 b- defN 16-Apr-04 20:36 org/slf4j/spi/LocationAwareLogger.class
...
-rw----     2.0 fat        0 bl defN 16-Jul-12 10:44 org/apache/commons/logging/
-rw----     2.0 fat        0 bl defN 16-Jul-12 10:44 org/apache/commons/logging/impl/
-rw----     2.0 fat     1991 b- defN 07-Nov-19 00:16 org/apache/commons/logging/impl/NoOpLog.class
...
1073 files, 2376880 bytes uncompressed, 1082322 bytes compressed:  54.5
```
