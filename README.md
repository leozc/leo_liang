A minimal example repo to try to sort out issues raised [here](https://goo.gl/w5Tbq0).


It seems it is a problem with the flag *scope='force'*, which pants-1.1.0-rc7 works fine but pants-1.0.0 fails.
Also verified the monolithic jar generated to ensure related classes are included. 


When on Pants 1.0.0

jar_library(
  name='thrift-0.9.2',
  jars=[
    jar('org.apache.thrift', 'libthrift', '0.9.2'),
   ],
   scope='forced',
)


```
11:42 $ ./pants clean-all binary -ldebug examples/src/java/HelloWorldClient:main
DEBUG] Parsing BUILD file BuildFile(examples/src/java/HelloWorldClient/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)).
DEBUG] Adding TargetAddressable(target_type=<class 'pants.backend.jvm.targets.jvm_app.JvmApp'>, name=main, **kwargs=...) to the BuildFileParser address map with BuildFileAddress(BuildFile(examples/src/java/HelloWorldClient/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), main)
DEBUG] Adding TargetAddressable(target_type=<class 'pants.backend.jvm.targets.jvm_binary.JvmBinary'>, name=main-bin, **kwargs=...) to the BuildFileParser address map with BuildFileAddress(BuildFile(examples/src/java/HelloWorldClient/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), main-bin)
DEBUG] BuildFile(examples/src/java/HelloWorldClient/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)) produced the following Addressables:
DEBUG]   * BuildFileAddress(BuildFile(examples/src/java/HelloWorldClient/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), main): TargetAddressable(target_type=<class 'pants.backend.jvm.targets.jvm_app.JvmApp'>, name=main, **kwargs=...)
DEBUG]   * BuildFileAddress(BuildFile(examples/src/java/HelloWorldClient/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), main-bin): TargetAddressable(target_type=<class 'pants.backend.jvm.targets.jvm_binary.JvmBinary'>, name=main-bin, **kwargs=...)
DEBUG] excludes:

DEBUG] Targets after excludes: examples/src/java/HelloWorldClient:main
DEBUG] Excluded 0 targets.
DEBUG] Parsing BUILD file BuildFile(3rdparty/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)).
DEBUG] Adding TargetAddressable(target_type=<class 'pants.backend.jvm.targets.jar_library.JarLibrary'>, name=slf4j-simple, **kwargs=...) to the BuildFileParser address map with BuildFileAddress(BuildFile(3rdparty/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), slf4j-simple)
DEBUG] Adding TargetAddressable(target_type=<class 'pants.backend.jvm.targets.jar_library.JarLibrary'>, name=thrift-0.9.2, **kwargs=...) to the BuildFileParser address map with BuildFileAddress(BuildFile(3rdparty/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), thrift-0.9.2)
DEBUG] BuildFile(3rdparty/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)) produced the following Addressables:
DEBUG]   * BuildFileAddress(BuildFile(3rdparty/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), slf4j-simple): TargetAddressable(target_type=<class 'pants.backend.jvm.targets.jar_library.JarLibrary'>, name=slf4j-simple, **kwargs=...)
DEBUG]   * BuildFileAddress(BuildFile(3rdparty/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), thrift-0.9.2): TargetAddressable(target_type=<class 'pants.backend.jvm.targets.jar_library.JarLibrary'>, name=thrift-0.9.2, **kwargs=...)
DEBUG] Parsing BUILD file BuildFile(examples/src/thrift/hello_world_service/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)).
DEBUG] Adding TargetAddressable(target_type=<class 'pants.backend.codegen.targets.python_thrift_library.PythonThriftLibrary'>, name=hello-world-service-python, **kwargs=...) to the BuildFileParser address map with BuildFileAddress(BuildFile(examples/src/thrift/hello_world_service/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), hello-world-service-python)
DEBUG] Adding TargetAddressable(target_type=<class 'pants.backend.codegen.targets.java_thrift_library.JavaThriftLibrary'>, name=hello-world-service-java, **kwargs=...) to the BuildFileParser address map with BuildFileAddress(BuildFile(examples/src/thrift/hello_world_service/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), hello-world-service-java)
DEBUG] BuildFile(examples/src/thrift/hello_world_service/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)) produced the following Addressables:
DEBUG]   * BuildFileAddress(BuildFile(examples/src/thrift/hello_world_service/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), hello-world-service-java): TargetAddressable(target_type=<class 'pants.backend.codegen.targets.java_thrift_library.JavaThriftLibrary'>, name=hello-world-service-java, **kwargs=...)
DEBUG]   * BuildFileAddress(BuildFile(examples/src/thrift/hello_world_service/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), hello-world-service-python): TargetAddressable(target_type=<class 'pants.backend.codegen.targets.python_thrift_library.PythonThriftLibrary'>, name=hello-world-service-python, **kwargs=...)
DEBUG] Executing: git --git-dir=/Users/leo/dev/repro/leo_liang/.git --work-tree=/Users/leo/dev/repro/leo_liang rev-parse --abbrev-ref HEAD
DEBUG] Detected git repository at /Users/leo/dev/repro/leo_liang on branch master
DEBUG] Executing: git --git-dir=/Users/leo/dev/repro/leo_liang/.git --work-tree=/Users/leo/dev/repro/leo_liang rev-parse HEAD
DEBUG] Executing: git --git-dir=/Users/leo/dev/repro/leo_liang/.git --work-tree=/Users/leo/dev/repro/leo_liang rev-parse --abbrev-ref HEAD

11:42:40 00:00 [main]
               (To run a reporting server: ./pants server)
11:42:40 00:00   [setup]
11:42:40 00:00     [parse]
               Executing tasks in goals: clean-all -> bootstrap -> imports -> unpack-jars -> jvm-platform-validate -> deferred-sources -> gen -> resolve -> resources -> compile -> binary
11:42:40 00:00   [clean-all]
11:42:40 00:00     [ng-killall]INFO] killing nailgun server pid=59713
DEBUG] terminating java
DEBUG] sending signal 15 to pid 59713
DEBUG] successfully terminated pid 59713
DEBUG] purging metadata directory: /Users/leo/dev/repro/leo_liang/.pids/java
INFO] killing nailgun server pid=59727
DEBUG] terminating java
DEBUG] sending signal 15 to pid 59727
DEBUG] successfully terminated pid 59727
DEBUG] purging metadata directory: /Users/leo/dev/repro/leo_liang/.pids/java

11:42:40 00:00     [clean-all]
11:42:40 00:00     [kill-pantsd]DEBUG] purging metadata directory: /Users/leo/dev/repro/leo_liang/.pids/pantsd

11:42:40 00:00   [bootstrap]
11:42:40 00:00     [jar-dependency-management]DEBUG] Located Distribution(u'/Library/Java/JavaVirtualMachines/jdk1.8.0_91.jdk/Contents/Home/bin', minimum_version=None, maximum_version=None jdk=False) for constraints: minimum_version None, maximum_version None, jdk False

11:42:40 00:00     [bootstrap-jvm-tools]
11:42:40 00:00   [imports]
11:42:40 00:00     [ivy-imports]
11:42:40 00:00   [unpack-jars]
11:42:40 00:00     [unpack-jars]
11:42:40 00:00   [jvm-platform-validate]
11:42:40 00:00     [jvm-platform-validate]WARN] No default jvm platform is defined.

11:42:40 00:00       [cache]
                   No cached artifacts for 2 targets.
                   Invalidated 2 targets.
11:42:40 00:00   [deferred-sources]
11:42:40 00:00     [deferred-sources]
11:42:40 00:00   [gen]
11:42:40 00:00     [thrift]
11:42:40 00:00       [cache].
                   Using cached artifacts for 1 target.DEBUG] Skipping insert of existing artifact: CacheKey(id=u'examples.src.thrift.hello_world_service.hello-world-service-java', hash=u'ff4f1a420bee1f3510a6efc2a6319afb4ecedcba-TaskIdentityFingerprintStrategy.da39a3ee5e6b_53c4bb9c6553')

11:42:40 00:00     [protoc]
11:42:40 00:00     [antlr]
11:42:40 00:00     [ragel]
11:42:40 00:00     [jaxb]
11:42:40 00:00     [wire]
11:42:40 00:00   [resolve]
11:42:40 00:00     [ivy]
11:42:40 00:00       [cache].DEBUG] Performing a fetch using ivy.

11:42:40 00:00       [bootstrap-nailgun-server]DEBUG] Executing: /usr/bin/java -Xmx256m -Dsun.io.useCanonCaches=false -Divy.cache.dir=/Users/leo/.ivy2/pants -cp ../../../.ivy2/pants/org.apache.ivy/ivy/jars/ivy-2.4.0.jar org.apache.ivy.Main -ivy /Users/leo/dev/repro/leo_liang/.pants.d/ivy/640c3bc5590854777baa05c14410bfefd86663b1-IvyResolveFingerprintStrategy_53c4bb9c6553/fetch-ivy.xml -confs default -cachepath /Users/leo/dev/repro/leo_liang/.pants.d/ivy/640c3bc5590854777baa05c14410bfefd86663b1-IvyResolveFingerprintStrategy_53c4bb9c6553/classpath.raw.tmp.4e5369b8ce6c418eb307e57f058030c8 args={'stderr': <pants.util.rwbuf.FileBackedRWBuf object at 0x102358450>, 'stdout': <pants.util.rwbuf.FileBackedRWBuf object at 0x102358410>} at cwd=/Users/leo/dev/repro/leo_liang
DEBUG] Moved ivy classfile file to /Users/leo/dev/repro/leo_liang/.pants.d/ivy/640c3bc5590854777baa05c14410bfefd86663b1-IvyResolveFingerprintStrategy_53c4bb9c6553/classpath.raw

11:42:41 00:01       [cache].
                   Using cached artifacts for 2 targets.DEBUG] Performing a fetch using ivy.

11:42:41 00:01       [ivy-resolve]DEBUG] Nailgun ng_IvyResolve_resolve_ivy state: updated=False running=False fingerprint=None new_fingerprint=e0f423a27c217e3a3604fe8059151a7851b7705b distribution=None new_distribution=/usr/bin/java
DEBUG] Spawning nailgun server ng_IvyResolve_resolve_ivy with fingerprint=e0f423a27c217e3a3604fe8059151a7851b7705b, jvm_options=['-Xmx256m', u'-Dsun.io.useCanonCaches=false', u'-Divy.cache.dir=/Users/leo/.ivy2/pants', u'-Dpants.buildroot=/Users/leo/dev/repro/leo_liang', u'-Dpants.nailgun.owner=/Users/leo/dev/repro/leo_liang/.pants.d/ng/IvyResolve_resolve_ivy', u'-Dpants.nailgun.fingerprint=e0f423a27c217e3a3604fe8059151a7851b7705b'], classpath=['/Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/com.martiansoftware/nailgun-server/jars/nailgun-server-0.9.1.jar', '/Users/leo/.ivy2/pants/org.apache.ivy/ivy/jars/ivy-2.4.0.jar']
DEBUG] purging metadata directory: /Users/leo/dev/repro/leo_liang/.pids/ng_IvyResolve_resolve_ivy
DEBUG] Executing: /usr/bin/java -Xmx256m -Dsun.io.useCanonCaches=false -Divy.cache.dir=/Users/leo/.ivy2/pants -Dpants.buildroot=/Users/leo/dev/repro/leo_liang -Dpants.nailgun.owner=/Users/leo/dev/repro/leo_liang/.pants.d/ng/IvyResolve_resolve_ivy -Dpants.nailgun.fingerprint=e0f423a27c217e3a3604fe8059151a7851b7705b -cp .pants.d/ivy/jars/com.martiansoftware/nailgun-server/jars/nailgun-server-0.9.1.jar:../../../.ivy2/pants/org.apache.ivy/ivy/jars/ivy-2.4.0.jar com.martiansoftware.nailgun.NGServer :0 args={'close_fds': True, 'stdin': <open file u'/dev/null', mode 'r' at 0x101e82a50>, 'stderr': <open file u'/Users/leo/dev/repro/leo_liang/.pants.d/ng/IvyResolve_resolve_ivy/stderr', mode 'w' at 0x101e828a0>, 'stdout': <open file u'/Users/leo/dev/repro/leo_liang/.pants.d/ng/IvyResolve_resolve_ivy/stdout', mode 'w' at 0x101e829c0>} at cwd=/Users/leo/dev/repro/leo_liang
DEBUG] Spawned nailgun server ng_IvyResolve_resolve_ivy with fingerprint=e0f423a27c217e3a3604fe8059151a7851b7705b, pid=61376 port=50013
DEBUG] Verified new ng server is connectable at ('127.0.0.1', 50013)
DEBUG] Executing via NailgunClient(host=u'127.0.0.1', port=50013, workdir='/Users/leo/dev/repro/leo_liang'): /usr/bin/java -Xmx256m -Dsun.io.useCanonCaches=false -Divy.cache.dir=/Users/leo/.ivy2/pants -cp /Users/leo/.ivy2/pants/org.apache.ivy/ivy/jars/ivy-2.4.0.jar org.apache.ivy.Main -ivy /Users/leo/dev/repro/leo_liang/.pants.d/ivy/25e13febb7920701f75fd51810c1c0e1de267c7a/fetch-ivy.xml -confs default -cachepath /Users/leo/dev/repro/leo_liang/.pants.d/ivy/25e13febb7920701f75fd51810c1c0e1de267c7a/classpath.raw.tmp.cb46d5f5da1147fb8bcd70169adf8db8
DEBUG] Moved ivy classfile file to /Users/leo/dev/repro/leo_liang/.pants.d/ivy/25e13febb7920701f75fd51810c1c0e1de267c7a/classpath.raw
DEBUG] Parsing ivy report /Users/leo/dev/repro/leo_liang/.pants.d/ivy/25e13febb7920701f75fd51810c1c0e1de267c7a/fetch-report-default.xml

11:42:42 00:02   [resources]
11:42:42 00:02     [prepare]
11:42:42 00:02     [services]
11:42:42 00:02   [compile]
11:42:42 00:02     [compile-jvm-prep-command]
11:42:42 00:02       [jvm_prep_command]
11:42:42 00:02     [compile-prep-command]
11:42:42 00:02     [compile]
11:42:42 00:02     [zinc]
11:42:42 00:02       [cache]
                   No cached artifacts for 2 targets.
                   Invalidated 2 targets.
11:42:42 00:02       [isolation-zinc-pool-bootstrap]
                   [1/2] Compiling 1 zinc source in 1 target (.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current:examples.src.thrift.hello_world_service.hello-world-service-java).
11:42:42 00:02       [compile]

11:42:42 00:02         [cache].DEBUG] Performing a fetch using ivy.

11:42:42 00:02         [bootstrap-scalac_2_10]DEBUG] Executing: /usr/bin/java -Xmx256m -Dsun.io.useCanonCaches=false -Divy.cache.dir=/Users/leo/.ivy2/pants -cp ../../../.ivy2/pants/org.apache.ivy/ivy/jars/ivy-2.4.0.jar org.apache.ivy.Main -ivy /Users/leo/dev/repro/leo_liang/.pants.d/ivy/f97279e7cb29a9c518d7b58e13d214fbf819b8f1-IvyResolveFingerprintStrategy_53c4bb9c6553/fetch-ivy.xml -confs default -cachepath /Users/leo/dev/repro/leo_liang/.pants.d/ivy/f97279e7cb29a9c518d7b58e13d214fbf819b8f1-IvyResolveFingerprintStrategy_53c4bb9c6553/classpath.raw.tmp.9afb3bef28d646d18740d3e31a0ab7f7 args={'stderr': <pants.util.rwbuf.FileBackedRWBuf object at 0x1024b41d0>, 'stdout': <pants.util.rwbuf.FileBackedRWBuf object at 0x1024b4890>} at cwd=/Users/leo/dev/repro/leo_liang
DEBUG] Moved ivy classfile file to /Users/leo/dev/repro/leo_liang/.pants.d/ivy/f97279e7cb29a9c518d7b58e13d214fbf819b8f1-IvyResolveFingerprintStrategy_53c4bb9c6553/classpath.raw

11:42:42 00:02         [cache].DEBUG] Performing a fetch using ivy.

11:42:42 00:02         [bootstrap-compiler-interface]DEBUG] Executing: /usr/bin/java -Xmx256m -Dsun.io.useCanonCaches=false -Divy.cache.dir=/Users/leo/.ivy2/pants -cp ../../../.ivy2/pants/org.apache.ivy/ivy/jars/ivy-2.4.0.jar org.apache.ivy.Main -ivy /Users/leo/dev/repro/leo_liang/.pants.d/ivy/0885000845a93e60e53756f74269649c979e562f-IvyResolveFingerprintStrategy_53c4bb9c6553/fetch-ivy.xml -confs default -cachepath /Users/leo/dev/repro/leo_liang/.pants.d/ivy/0885000845a93e60e53756f74269649c979e562f-IvyResolveFingerprintStrategy_53c4bb9c6553/classpath.raw.tmp.aa50aaa3159345978d4cf3c2bfdba951 args={'stderr': <pants.util.rwbuf.FileBackedRWBuf object at 0x1024cb390>, 'stdout': <pants.util.rwbuf.FileBackedRWBuf object at 0x1024cb310>} at cwd=/Users/leo/dev/repro/leo_liang
DEBUG] Moved ivy classfile file to /Users/leo/dev/repro/leo_liang/.pants.d/ivy/0885000845a93e60e53756f74269649c979e562f-IvyResolveFingerprintStrategy_53c4bb9c6553/classpath.raw

11:42:43 00:03         [cache].DEBUG] Performing a fetch using ivy.

11:42:43 00:03         [bootstrap-sbt-interface]DEBUG] Executing: /usr/bin/java -Xmx256m -Dsun.io.useCanonCaches=false -Divy.cache.dir=/Users/leo/.ivy2/pants -cp ../../../.ivy2/pants/org.apache.ivy/ivy/jars/ivy-2.4.0.jar org.apache.ivy.Main -ivy /Users/leo/dev/repro/leo_liang/.pants.d/ivy/bdc54b129545fa3fc15b349b1dad2b1f7fa6e0ee-IvyResolveFingerprintStrategy_53c4bb9c6553/fetch-ivy.xml -confs default -cachepath /Users/leo/dev/repro/leo_liang/.pants.d/ivy/bdc54b129545fa3fc15b349b1dad2b1f7fa6e0ee-IvyResolveFingerprintStrategy_53c4bb9c6553/classpath.raw.tmp.8ba616c3d2e84592924e319c0caa8a7b args={'stderr': <pants.util.rwbuf.FileBackedRWBuf object at 0x1024cb7d0>, 'stdout': <pants.util.rwbuf.FileBackedRWBuf object at 0x1024cb6d0>} at cwd=/Users/leo/dev/repro/leo_liang
DEBUG] Moved ivy classfile file to /Users/leo/dev/repro/leo_liang/.pants.d/ivy/bdc54b129545fa3fc15b349b1dad2b1f7fa6e0ee-IvyResolveFingerprintStrategy_53c4bb9c6553/classpath.raw

11:42:43 00:03         [cache].
                     Using cached artifacts for 1 target.DEBUG] Using previous fetch.

11:42:44 00:04         [zinc]
                       DEBUG] Nailgun ng_ZincCompile_compile_zinc state: updated=False running=False fingerprint=None new_fingerprint=7d4b3215fafec59dc2434ccb63459723a52d245f distribution=None new_distribution=/usr/bin/java
DEBUG] Spawning nailgun server ng_ZincCompile_compile_zinc with fingerprint=7d4b3215fafec59dc2434ccb63459723a52d245f, jvm_options=['-Dfile.encoding=UTF-8', '-Dzinc.analysis.cache.limit=1000', '-Djava.awt.headless=true', '-Xmx2g', u'-Dpants.buildroot=/Users/leo/dev/repro/leo_liang', u'-Dpants.nailgun.owner=/Users/leo/dev/repro/leo_liang/.pants.d/ng/ZincCompile_compile_zinc', u'-Dpants.nailgun.fingerprint=7d4b3215fafec59dc2434ccb63459723a52d245f'], classpath=['/Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/com.martiansoftware/nailgun-server/jars/nailgun-server-0.9.1.jar', u'/Users/leo/dev/repro/leo_liang/.pants.d/bootstrap/bootstrap-jvm-tools/tool_cache/shaded_jars/org.pantsbuild.zinc.Main-13732463e825cfa7f8fba03f7518b829ae67c22a-ShadedToolFingerprintStrategy_53c4bb9c6553.jar', u'/Library/Java/JavaVirtualMachines/jdk1.8.0_91.jdk/Contents/Home/lib/tools.jar']
DEBUG] purging metadata directory: /Users/leo/dev/repro/leo_liang/.pids/ng_ZincCompile_compile_zinc
DEBUG] Executing: /usr/bin/java -Dfile.encoding=UTF-8 -Dzinc.analysis.cache.limit=1000 -Djava.awt.headless=true -Xmx2g -Dpants.buildroot=/Users/leo/dev/repro/leo_liang -Dpants.nailgun.owner=/Users/leo/dev/repro/leo_liang/.pants.d/ng/ZincCompile_compile_zinc -Dpants.nailgun.fingerprint=7d4b3215fafec59dc2434ccb63459723a52d245f -cp .pants.d/ivy/jars/com.martiansoftware/nailgun-server/jars/nailgun-server-0.9.1.jar:.pants.d/bootstrap/bootstrap-jvm-tools/tool_cache/shaded_jars/org.pantsbuild.zinc.Main-13732463e825cfa7f8fba03f7518b829ae67c22a-ShadedToolFingerprintStrategy_53c4bb9c6553.jar:../../../../../Library/Java/JavaVirtualMachines/jdk1.8.0_91.jdk/Contents/Home/lib/tools.jar com.martiansoftware.nailgun.NGServer :0 args={'close_fds': True, 'stdin': <open file u'/dev/null', mode 'r' at 0x1024db150>, 'stderr': <open file u'/Users/leo/dev/repro/leo_liang/.pants.d/ng/ZincCompile_compile_zinc/stderr', mode 'w' at 0x1024db1e0>, 'stdout': <open file u'/Users/leo/dev/repro/leo_liang/.pants.d/ng/ZincCompile_compile_zinc/stdout', mode 'w' at 0x1024db0c0>} at cwd=/Users/leo/dev/repro/leo_liang
DEBUG] Spawned nailgun server ng_ZincCompile_compile_zinc with fingerprint=7d4b3215fafec59dc2434ccb63459723a52d245f, pid=61388 port=50020
DEBUG] Verified new ng server is connectable at ('127.0.0.1', 50020)
DEBUG] Executing via NailgunClient(host=u'127.0.0.1', port=50020, workdir='/Users/leo/dev/repro/leo_liang'): /usr/bin/java -Dfile.encoding=UTF-8 -Dzinc.analysis.cache.limit=1000 -Djava.awt.headless=true -Xmx2g -cp /Users/leo/dev/repro/leo_liang/.pants.d/bootstrap/bootstrap-jvm-tools/tool_cache/shaded_jars/org.pantsbuild.zinc.Main-13732463e825cfa7f8fba03f7518b829ae67c22a-ShadedToolFingerprintStrategy_53c4bb9c6553.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_91.jdk/Contents/Home/lib/tools.jar org.pantsbuild.zinc.Main -log-level info -analysis-cache /Users/leo/dev/repro/leo_liang/.pants.d/compile/zinc/252d64521cf9/.pants.d.gen.thrift.252d64521cf9.examples.src.thrift.hello_world_service.hello-world-service-java.current.examples.src.thrift.hello_world_service.hello-world-service-java/current/.pants.d.gen.thrift.252d64521cf9.examples.src.thrift.hello_world_service.hello-world-service-java.current.examples.src.thrift.hello_world_service.hello-world-service-java.analysis.tmp -classpath /Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.scala-lang/scala-compiler/jars/scala-compiler-2.10.4.jar:/Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.scala-lang/scala-library/jars/scala-library-2.10.4.jar:/Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.scala-lang/scala-reflect/jars/scala-reflect-2.10.4.jar:/Users/leo/dev/repro/leo_liang/.pants.d/compile/zinc/252d64521cf9/.pants.d.gen.thrift.252d64521cf9.examples.src.thrift.hello_world_service.hello-world-service-java.current.examples.src.thrift.hello_world_service.hello-world-service-java/current/classes -d /Users/leo/dev/repro/leo_liang/.pants.d/compile/zinc/252d64521cf9/.pants.d.gen.thrift.252d64521cf9.examples.src.thrift.hello_world_service.hello-world-service-java.current.examples.src.thrift.hello_world_service.hello-world-service-java/current/classes -no-name-hashing -compiler-interface /Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/com.typesafe.sbt/compiler-interface/jars/compiler-interface-0.13.9-sources.jar -sbt-interface /Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/com.typesafe.sbt/sbt-interface/jars/sbt-interface-0.13.9.jar -scala-path /Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.scala-lang/scala-compiler/jars/scala-compiler-2.10.4.jar:/Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.scala-lang/scala-library/jars/scala-library-2.10.4.jar:/Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.scala-lang/scala-reflect/jars/scala-reflect-2.10.4.jar -analysis-map /Users/leo/dev/repro/leo_liang/.pants.d/compile/zinc/252d64521cf9/.pants.d.gen.thrift.252d64521cf9.examples.src.thrift.hello_world_service.hello-world-service-java.current.examples.src.thrift.hello_world_service.hello-world-service-java/current/classes:/Users/leo/dev/repro/leo_liang/.pants.d/compile/zinc/252d64521cf9/.pants.d.gen.thrift.252d64521cf9.examples.src.thrift.hello_world_service.hello-world-service-java.current.examples.src.thrift.hello_world_service.hello-world-service-java/current/.pants.d.gen.thrift.252d64521cf9.examples.src.thrift.hello_world_service.hello-world-service-java.current.examples.src.thrift.hello_world_service.hello-world-service-java.analysis -C-encoding -CUTF-8 -S-encoding -SUTF-8 -S-g:vars -C-deprecation -C-Xlint:all -C-Xlint:-serial -C-Xlint:-path -S-deprecation -S-unchecked -S-Xlint -C-source -C1.8 -C-target -C1.8 .pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java
[info] Compiling 1 Java source to /Users/leo/dev/repro/leo_liang/.pants.d/compile/zinc/252d64521cf9/.pants.d.gen.thrift.252d64521cf9.examples.src.thrift.hello_world_service.hello-world-service-java.current.examples.src.thrift.hello_world_service.hello-world-service-java/current/classes...
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:9: package org.apache.thrift.scheme does not exist
                       [error] import org.apache.thrift.scheme.IScheme;
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:10: package org.apache.thrift.scheme does not exist
                       [error] import org.apache.thrift.scheme.SchemeFactory;
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:11: package org.apache.thrift.scheme does not exist
                       [error] import org.apache.thrift.scheme.StandardScheme;
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:13: package org.apache.thrift.scheme does not exist
                       [error] import org.apache.thrift.scheme.TupleScheme;
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:14: package org.apache.thrift.protocol does not exist
                       [error] import org.apache.thrift.protocol.TTupleProtocol;
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:15: package org.apache.thrift.protocol does not exist
                       [error] import org.apache.thrift.protocol.TProtocolException;
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:16: package org.apache.thrift does not exist
                       [error] import org.apache.thrift.EncodingUtils;
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:17: package org.apache.thrift does not exist
                       [error] import org.apache.thrift.TException;
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:18: package org.apache.thrift.async does not exist
                       [error] import org.apache.thrift.async.AsyncMethodCallback;
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:19: package org.apache.thrift.server.AbstractNonblockingServer does not exist
                       [error] import org.apache.thrift.server.AbstractNonblockingServer.*;
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:33: package org.slf4j does not exist
                       [error] import org.slf4j.Logger;
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:34: package org.slf4j does not exist
                       [error] import org.slf4j.LoggerFactory;
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:48: package org.apache.thrift does not exist
                       [error]     public void ping() throws org.apache.thrift.TException;
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:54: package org.apache.thrift.async does not exist
                       [error]     public void ping(org.apache.thrift.async.AsyncMethodCallback resultHandler) throws org.apache.thrift.TException;
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:54: package org.apache.thrift does not exist
                       [error]     public void ping(org.apache.thrift.async.AsyncMethodCallback resultHandler) throws org.apache.thrift.TException;
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:58: package org.apache.thrift does not exist
                       [error]   public static class Client extends org.apache.thrift.TServiceClient implements Iface {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:69: package org.apache.thrift.protocol does not exist
                       [error]     public Client(org.apache.thrift.protocol.TProtocol prot)
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:74: package org.apache.thrift.protocol does not exist
                       [error]     public Client(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:74: package org.apache.thrift.protocol does not exist
                       [error]     public Client(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:78: package org.apache.thrift does not exist
                       [error]     public void ping() throws org.apache.thrift.TException
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:84: package org.apache.thrift does not exist
                       [error]     public void send_ping() throws org.apache.thrift.TException
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:90: package org.apache.thrift does not exist
                       [error]     public void recv_ping() throws org.apache.thrift.TException
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:59: package org.apache.thrift does not exist
                       [error]     public static class Factory implements org.apache.thrift.TServiceClientFactory<Client> {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:61: package org.apache.thrift.protocol does not exist
                       [error]       public Client getClient(org.apache.thrift.protocol.TProtocol prot) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:64: package org.apache.thrift.protocol does not exist
                       [error]       public Client getClient(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:64: package org.apache.thrift.protocol does not exist
                       [error]       public Client getClient(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:98: package org.apache.thrift.async does not exist
                       [error]   public static class AsyncClient extends org.apache.thrift.async.TAsyncClient implements AsyncIface {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:111: package org.apache.thrift.protocol does not exist
                       [error]     public AsyncClient(org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.transport.TNonblockingTransport transport) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:111: package org.apache.thrift.async does not exist
                       [error]     public AsyncClient(org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.transport.TNonblockingTransport transport) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:111: package org.apache.thrift.transport does not exist
                       [error]     public AsyncClient(org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.transport.TNonblockingTransport transport) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:115: package org.apache.thrift.async does not exist
                       [error]     public void ping(org.apache.thrift.async.AsyncMethodCallback resultHandler) throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:115: package org.apache.thrift does not exist
                       [error]     public void ping(org.apache.thrift.async.AsyncMethodCallback resultHandler) throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:99: package org.apache.thrift.async does not exist
                       [error]     public static class Factory implements org.apache.thrift.async.TAsyncClientFactory<AsyncClient> {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:100: package org.apache.thrift.async does not exist
                       [error]       private org.apache.thrift.async.TAsyncClientManager clientManager;
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:101: package org.apache.thrift.protocol does not exist
                       [error]       private org.apache.thrift.protocol.TProtocolFactory protocolFactory;
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:102: package org.apache.thrift.async does not exist
                       [error]       public Factory(org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.protocol.TProtocolFactory protocolFactory) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:102: package org.apache.thrift.protocol does not exist
                       [error]       public Factory(org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.protocol.TProtocolFactory protocolFactory) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:106: package org.apache.thrift.transport does not exist
                       [error]       public AsyncClient getAsyncClient(org.apache.thrift.transport.TNonblockingTransport transport) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:122: package org.apache.thrift.async does not exist
                       [error]     public static class ping_call extends org.apache.thrift.async.TAsyncMethodCall {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:123: package org.apache.thrift.async does not exist
                       [error]       public ping_call(org.apache.thrift.async.AsyncMethodCallback resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:123: package org.apache.thrift.async does not exist
                       [error]       public ping_call(org.apache.thrift.async.AsyncMethodCallback resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:123: package org.apache.thrift.protocol does not exist
                       [error]       public ping_call(org.apache.thrift.async.AsyncMethodCallback resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:123: package org.apache.thrift.transport does not exist
                       [error]       public ping_call(org.apache.thrift.async.AsyncMethodCallback resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:123: package org.apache.thrift does not exist
                       [error]       public ping_call(org.apache.thrift.async.AsyncMethodCallback resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:127: package org.apache.thrift.protocol does not exist
                       [error]       public void write_args(org.apache.thrift.protocol.TProtocol prot) throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:127: package org.apache.thrift does not exist
                       [error]       public void write_args(org.apache.thrift.protocol.TProtocol prot) throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:134: package org.apache.thrift does not exist
                       [error]       public void getResult() throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:146: package org.apache.thrift does not exist
                       [error]   public static class Processor<I extends Iface> extends org.apache.thrift.TBaseProcessor<I> implements org.apache.thrift.TProcessor {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:146: package org.apache.thrift does not exist
                       [error]   public static class Processor<I extends Iface> extends org.apache.thrift.TBaseProcessor<I> implements org.apache.thrift.TProcessor {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:147: cannot find symbol
                       [error]   symbol:   class Logger
                       [error]   location: class hello_world_service.HelloWorldService.Processor<I>
                       [error]     private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:152: package org.apache.thrift does not exist
                       [error]     protected Processor(I iface, Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:152: package org.apache.thrift does not exist
                       [error]     protected Processor(I iface, Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:156: package org.apache.thrift does not exist
                       [error]     private static <I extends Iface> Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> getProcessMap(Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:156: package org.apache.thrift does not exist
                       [error]     private static <I extends Iface> Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> getProcessMap(Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:156: package org.apache.thrift does not exist
                       [error]     private static <I extends Iface> Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> getProcessMap(Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:156: package org.apache.thrift does not exist
                       [error]     private static <I extends Iface> Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> getProcessMap(Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:161: package org.apache.thrift does not exist
                       [error]     public static class ping<I extends Iface> extends org.apache.thrift.ProcessFunction<I, ping_args> {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:250: package org.apache.thrift does not exist
                       [error]   public static class ping_args implements org.apache.thrift.TBase<ping_args, ping_args._Fields>, java.io.Serializable, Cloneable, Comparable<ping_args>   {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:262: package org.apache.thrift does not exist
                       [error]     public enum _Fields implements org.apache.thrift.TFieldIdEnum {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:498: package org.apache.thrift does not exist
                       [error]   public static class ping_result implements org.apache.thrift.TBase<ping_result, ping_result._Fields>, java.io.Serializable, Cloneable, Comparable<ping_result>   {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:510: package org.apache.thrift does not exist
                       [error]     public enum _Fields implements org.apache.thrift.TFieldIdEnum {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:174: package org.apache.thrift does not exist
                       [error]       public ping_result getResult(I iface, ping_args args) throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:251: package org.apache.thrift.protocol does not exist
                       [error]     private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ping_args");
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:254: cannot find symbol
                       [error]   symbol:   class IScheme
                       [error]   location: class hello_world_service.HelloWorldService.ping_args
                       [error]     private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:254: cannot find symbol
                       [error]   symbol:   class SchemeFactory
                       [error]   location: class hello_world_service.HelloWorldService.ping_args
                       [error]     private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:316: package org.apache.thrift.meta_data does not exist
                       [error]     public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:400: package org.apache.thrift.protocol does not exist
                       [error]     public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:400: package org.apache.thrift does not exist
                       [error]     public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:404: package org.apache.thrift.protocol does not exist
                       [error]     public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:404: package org.apache.thrift does not exist
                       [error]     public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:417: package org.apache.thrift does not exist
                       [error]     public void validate() throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:499: package org.apache.thrift.protocol does not exist
                       [error]     private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ping_result");
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:502: cannot find symbol
                       [error]   symbol:   class IScheme
                       [error]   location: class hello_world_service.HelloWorldService.ping_result
                       [error]     private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:502: cannot find symbol
                       [error]   symbol:   class SchemeFactory
                       [error]   location: class hello_world_service.HelloWorldService.ping_result
                       [error]     private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:564: package org.apache.thrift.meta_data does not exist
                       [error]     public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:648: package org.apache.thrift.protocol does not exist
                       [error]     public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:648: package org.apache.thrift does not exist
                       [error]     public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:652: package org.apache.thrift.protocol does not exist
                       [error]     public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:652: package org.apache.thrift does not exist
                       [error]     public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:665: package org.apache.thrift does not exist
                       [error]     public void validate() throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:183: package org.apache.thrift does not exist
                       [error]   public static class AsyncProcessor<I extends AsyncIface> extends org.apache.thrift.TBaseAsyncProcessor<I> {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:184: cannot find symbol
                       [error]   symbol:   class Logger
                       [error]   location: class hello_world_service.HelloWorldService.AsyncProcessor<I>
                       [error]     private static final Logger LOGGER = LoggerFactory.getLogger(AsyncProcessor.class.getName());
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:189: package org.apache.thrift does not exist
                       [error]     protected AsyncProcessor(I iface, Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:189: package org.apache.thrift does not exist
                       [error]     protected AsyncProcessor(I iface, Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:193: package org.apache.thrift does not exist
                       [error]     private static <I extends AsyncIface> Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase,?>> getProcessMap(Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:193: package org.apache.thrift does not exist
                       [error]     private static <I extends AsyncIface> Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase,?>> getProcessMap(Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:193: package org.apache.thrift does not exist
                       [error]     private static <I extends AsyncIface> Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase,?>> getProcessMap(Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:193: package org.apache.thrift does not exist
                       [error]     private static <I extends AsyncIface> Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase,?>> getProcessMap(Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:198: package org.apache.thrift does not exist
                       [error]     public static class ping<I extends AsyncIface> extends org.apache.thrift.AsyncProcessFunction<I, ping_args, Void> {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:207: cannot find symbol
                       [error]   symbol:   class AsyncFrameBuffer
                       [error]   location: class hello_world_service.HelloWorldService.AsyncProcessor.ping<I>
                       [error]       public AsyncMethodCallback<Void> getResultHandler(final AsyncFrameBuffer fb, final int seqid) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:207: cannot find symbol
                       [error]   symbol:   class AsyncMethodCallback
                       [error]   location: class hello_world_service.HelloWorldService.AsyncProcessor.ping<I>
                       [error]       public AsyncMethodCallback<Void> getResultHandler(final AsyncFrameBuffer fb, final int seqid) {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:243: package org.apache.thrift.async does not exist
                       [error]       public void start(I iface, ping_args args, org.apache.thrift.async.AsyncMethodCallback<Void> resultHandler) throws TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:243: cannot find symbol
                       [error]   symbol:   class TException
                       [error]   location: class hello_world_service.HelloWorldService.AsyncProcessor.ping<I>
                       [error]       public void start(I iface, ping_args args, org.apache.thrift.async.AsyncMethodCallback<Void> resultHandler) throws TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:438: cannot find symbol
                       [error]   symbol:   class SchemeFactory
                       [error]   location: class hello_world_service.HelloWorldService.ping_args
                       [error]     private static class ping_argsStandardSchemeFactory implements SchemeFactory {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:444: cannot find symbol
                       [error]   symbol:   class StandardScheme
                       [error]   location: class hello_world_service.HelloWorldService.ping_args
                       [error]     private static class ping_argsStandardScheme extends StandardScheme<ping_args> {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:446: package org.apache.thrift.protocol does not exist
                       [error]       public void read(org.apache.thrift.protocol.TProtocol iprot, ping_args struct) throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:446: package org.apache.thrift does not exist
                       [error]       public void read(org.apache.thrift.protocol.TProtocol iprot, ping_args struct) throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:467: package org.apache.thrift.protocol does not exist
                       [error]       public void write(org.apache.thrift.protocol.TProtocol oprot, ping_args struct) throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:467: package org.apache.thrift does not exist
                       [error]       public void write(org.apache.thrift.protocol.TProtocol oprot, ping_args struct) throws org.apache.thrift.TException {
                       [error] /Users/leo/dev/repro/leo_liang/.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current/hello_world_service/HelloWorldService.java:477: cannot find symbol
                       [error]   symbol:   class SchemeFactory
                       [error]   location: class hello_world_service.HelloWorldService.ping_args
                       [error]     private static class ping_argsTupleSchemeFactory implements SchemeFactory {
                       [error] Compile failed at Jul 12, 2016 11:42:45 AM [0.966s]

                   compile(.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current:examples.src.thrift.hello_world_service.hello-world-service-java) failed: Zinc compile failed.
FAILURE: Compilation failure: Failed jobs: compile(.pants.d/gen/thrift/252d64521cf9/examples.src.thrift.hello_world_service.hello-world-service-java/current:examples.src.thrift.hello_world_service.hello-world-service-java)


               Waiting for background workers to finish.
```

When on Pants 1.1.0-rc7

jar_library(
  name='thrift-0.9.2',
  jars=[
    jar('org.apache.thrift', 'libthrift', '0.9.2'),
   ],
   scope='forced',
)


```
11:45 $ ./pants clean-all binary -ldebug examples/src/java/HelloWorldClient:main
DEBUG] ProjectTree ignore_patterns: ['.*', '/dist/']
DEBUG] Parsing BUILD file BuildFile(examples/src/java/HelloWorldClient/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)).
DEBUG] Adding TargetAddressable(target_type=<class 'pants.backend.jvm.targets.jvm_app.JvmApp'>, name=main, **kwargs=...) to the BuildFileParser address map with BuildFileAddress(BuildFile(examples/src/java/HelloWorldClient/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), main)
DEBUG] Adding TargetAddressable(target_type=<class 'pants.backend.jvm.targets.jvm_binary.JvmBinary'>, name=main-bin, **kwargs=...) to the BuildFileParser address map with BuildFileAddress(BuildFile(examples/src/java/HelloWorldClient/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), main-bin)
DEBUG] BuildFile(examples/src/java/HelloWorldClient/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)) produced the following Addressables:
DEBUG]   * BuildFileAddress(BuildFile(examples/src/java/HelloWorldClient/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), main): TargetAddressable(target_type=<class 'pants.backend.jvm.targets.jvm_app.JvmApp'>, name=main, **kwargs=...)
DEBUG]   * BuildFileAddress(BuildFile(examples/src/java/HelloWorldClient/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), main-bin): TargetAddressable(target_type=<class 'pants.backend.jvm.targets.jvm_binary.JvmBinary'>, name=main-bin, **kwargs=...)
DEBUG] excludes:

DEBUG] Targets after excludes: examples/src/java/HelloWorldClient:main
DEBUG] Excluded 0 targets.
DEBUG] Parsing BUILD file BuildFile(3rdparty/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)).
DEBUG] Adding TargetAddressable(target_type=<class 'pants.backend.jvm.targets.jar_library.JarLibrary'>, name=slf4j-simple, **kwargs=...) to the BuildFileParser address map with BuildFileAddress(BuildFile(3rdparty/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), slf4j-simple)
DEBUG] Adding TargetAddressable(target_type=<class 'pants.backend.jvm.targets.jar_library.JarLibrary'>, name=thrift-0.9.2, **kwargs=...) to the BuildFileParser address map with BuildFileAddress(BuildFile(3rdparty/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), thrift-0.9.2)
DEBUG] BuildFile(3rdparty/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)) produced the following Addressables:
DEBUG]   * BuildFileAddress(BuildFile(3rdparty/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), slf4j-simple): TargetAddressable(target_type=<class 'pants.backend.jvm.targets.jar_library.JarLibrary'>, name=slf4j-simple, **kwargs=...)
DEBUG]   * BuildFileAddress(BuildFile(3rdparty/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), thrift-0.9.2): TargetAddressable(target_type=<class 'pants.backend.jvm.targets.jar_library.JarLibrary'>, name=thrift-0.9.2, **kwargs=...)
DEBUG] Parsing BUILD file BuildFile(examples/src/thrift/hello_world_service/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)).
DEBUG] Adding TargetAddressable(target_type=<class 'pants.backend.codegen.targets.python_thrift_library.PythonThriftLibrary'>, name=hello-world-service-python, **kwargs=...) to the BuildFileParser address map with BuildFileAddress(BuildFile(examples/src/thrift/hello_world_service/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), hello-world-service-python)
DEBUG] Adding TargetAddressable(target_type=<class 'pants.backend.codegen.targets.java_thrift_library.JavaThriftLibrary'>, name=hello-world-service-java, **kwargs=...) to the BuildFileParser address map with BuildFileAddress(BuildFile(examples/src/thrift/hello_world_service/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), hello-world-service-java)
DEBUG] BuildFile(examples/src/thrift/hello_world_service/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)) produced the following Addressables:
DEBUG]   * BuildFileAddress(BuildFile(examples/src/thrift/hello_world_service/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), hello-world-service-java): TargetAddressable(target_type=<class 'pants.backend.codegen.targets.java_thrift_library.JavaThriftLibrary'>, name=hello-world-service-java, **kwargs=...)
DEBUG]   * BuildFileAddress(BuildFile(examples/src/thrift/hello_world_service/BUILD, FileSystemProjectTree(/Users/leo/dev/repro/leo_liang)), hello-world-service-python): TargetAddressable(target_type=<class 'pants.backend.codegen.targets.python_thrift_library.PythonThriftLibrary'>, name=hello-world-service-python, **kwargs=...)
DEBUG] Executing: git --git-dir=/Users/leo/dev/repro/leo_liang/.git --work-tree=/Users/leo/dev/repro/leo_liang rev-parse --abbrev-ref HEAD
DEBUG] Detected git repository at /Users/leo/dev/repro/leo_liang on branch master
DEBUG] Executing: git --git-dir=/Users/leo/dev/repro/leo_liang/.git --work-tree=/Users/leo/dev/repro/leo_liang rev-parse HEAD
DEBUG] Executing: git --git-dir=/Users/leo/dev/repro/leo_liang/.git --work-tree=/Users/leo/dev/repro/leo_liang rev-parse --abbrev-ref HEAD

11:45:56 00:00 [main]
               (To run a reporting server: ./pants server)
11:45:56 00:00   [setup]
11:45:56 00:00     [parse]
               Executing tasks in goals: clean-all -> bootstrap -> imports -> unpack-jars -> deferred-sources -> gen -> jvm-platform-validate -> resolve -> compile -> resources -> binary
11:45:56 00:00   [clean-all]
11:45:56 00:00     [ng-killall]
11:45:56 00:00     [clean-all]DEBUG] Moving trash to /Users/leo/dev/repro/leo_liang/.pants_cleanall4hnTvf for deletion
INFO] For async removal, run `./pants clean-all --async`

11:45:56 00:00     [kill-pantsd]DEBUG] purging metadata directory: /Users/leo/dev/repro/leo_liang/.pids/pantsd

11:45:56 00:00   [bootstrap]
11:45:56 00:00     [substitute-aliased-targets]
11:45:56 00:00     [jar-dependency-management]DEBUG] None is not a valid distribution because: Failed to locate the java executable, Distribution('/Users/leo/google-cloud-sdk/bin', minimum_version=None, maximum_version=None jdk=False) does not appear to be a valid JRE distribution
DEBUG] None is not a valid distribution because: Failed to locate the java executable, Distribution('/usr/local/bin', minimum_version=None, maximum_version=None jdk=False) does not appear to be a valid JRE distribution
DEBUG] Located Distribution(u'/Library/Java/JavaVirtualMachines/jdk1.8.0_91.jdk/Contents/Home/bin', minimum_version=None, maximum_version=None jdk=False) for constraints: minimum_version None, maximum_version None, jdk False

11:45:57 00:01     [bootstrap-jvm-tools]
11:45:57 00:01     [provide-tools-jar]
11:45:57 00:01   [imports]
11:45:57 00:01     [ivy-imports]
11:45:57 00:01   [unpack-jars]
11:45:57 00:01     [unpack-jars]
11:45:57 00:01   [deferred-sources]
11:45:57 00:01     [deferred-sources]
11:45:57 00:01   [gen]
11:45:57 00:01     [thrift]
11:45:57 00:01       [cache].
                   Using cached artifacts for 1 target.DEBUG] Skipping insert of existing artifact: CacheKey(id=u'examples.src.thrift.hello_world_service.hello-world-service-java', hash=u'0a16884cce13d7f02026499714878ae984c25bd5-TaskIdentityFingerprintStrategy.da39a3ee5e6b_53c4bb9c6553')

11:45:57 00:01     [protoc]
11:45:57 00:01     [antlr]
11:45:57 00:01     [ragel]
11:45:57 00:01     [jaxb]
11:45:57 00:01     [wire]
11:45:57 00:01   [jvm-platform-validate]
11:45:57 00:01     [jvm-platform-validate]WARN] No default jvm platform is defined.

11:45:57 00:01       [cache]
                   No cached artifacts for 3 targets.
                   Invalidated 3 targets.
11:45:57 00:01   [resolve]
11:45:57 00:01     [ivy]
11:45:57 00:01       [cache].DEBUG] Performing a fetch using ivy.

11:45:57 00:01       [bootstrap-nailgun-server]DEBUG] Executing: /usr/bin/java -Xmx256m -Dsun.io.useCanonCaches=false -Divy.cache.dir=/Users/leo/.ivy2/pants -cp ../../../.ivy2/pants/org.apache.ivy/ivy/jars/ivy-2.4.0.jar org.apache.ivy.Main -ivy /Users/leo/dev/repro/leo_liang/.pants.d/ivy/b9e43ba37c9a5570d88f0dcf01f4f5381684963b-IvyResolveFingerprintStrategy_53c4bb9c6553/fetch-ivy.xml -confs default -cachepath /Users/leo/dev/repro/leo_liang/.pants.d/ivy/b9e43ba37c9a5570d88f0dcf01f4f5381684963b-IvyResolveFingerprintStrategy_53c4bb9c6553/classpath.raw.tmp.a32c01c1fd99454283aad88ab0917110 args={'stderr': <pants.util.rwbuf.FileBackedRWBuf object at 0x10d1e1cd0>, 'stdout': <pants.util.rwbuf.FileBackedRWBuf object at 0x10d1e1c90>} at cwd=/Users/leo/dev/repro/leo_liang
DEBUG] Moved ivy classfile file to /Users/leo/dev/repro/leo_liang/.pants.d/ivy/b9e43ba37c9a5570d88f0dcf01f4f5381684963b-IvyResolveFingerprintStrategy_53c4bb9c6553/classpath.raw

11:45:57 00:01       [cache].
                   Using cached artifacts for 2 targets.DEBUG] Performing a fetch using ivy.

11:45:57 00:01       [ivy-resolve]DEBUG] Nailgun ng_IvyResolve_resolve_ivy state: updated=False running=True fingerprint=e0f423a27c217e3a3604fe8059151a7851b7705b new_fingerprint=e0f423a27c217e3a3604fe8059151a7851b7705b distribution=/usr/bin/java new_distribution=/usr/bin/java
DEBUG] Executing via NailgunClient(host=u'127.0.0.1', port=50013, workdir='/Users/leo/dev/repro/leo_liang'): /usr/bin/java -Xmx256m -Dsun.io.useCanonCaches=false -Divy.cache.dir=/Users/leo/.ivy2/pants -cp /Users/leo/.ivy2/pants/org.apache.ivy/ivy/jars/ivy-2.4.0.jar org.apache.ivy.Main -ivy /Users/leo/dev/repro/leo_liang/.pants.d/ivy/5e11e7f2ede4abffe6e3b52138fd1546700c9329/fetch-ivy.xml -confs default -cachepath /Users/leo/dev/repro/leo_liang/.pants.d/ivy/5e11e7f2ede4abffe6e3b52138fd1546700c9329/classpath.raw.tmp.94341db60d5c4905ac3ad4f26f1d2d3e
DEBUG] Moved ivy classfile file to /Users/leo/dev/repro/leo_liang/.pants.d/ivy/5e11e7f2ede4abffe6e3b52138fd1546700c9329/classpath.raw
DEBUG] Parsing ivy report /Users/leo/dev/repro/leo_liang/.pants.d/ivy/5e11e7f2ede4abffe6e3b52138fd1546700c9329/fetch-report-default.xml

11:45:57 00:01   [compile]
11:45:57 00:01     [compile-jvm-prep-command]
11:45:57 00:01       [jvm_prep_command]
11:45:57 00:01     [compile-prep-command]
11:45:57 00:01     [compile]
11:45:57 00:01     [zinc]
11:45:58 00:02       [cache]..DEBUG] Skipping insert of existing artifact: CacheKey(id=u'.pants.d.gen.thrift.e300ff222c82.examples.src.thrift.hello_world_service.hello-world-service-java.current.examples.src.thrift.hello_world_service.hello-world-service-java', hash=u'81b24764f0b40118f719c781f471093c3792ae89-ResolvedJarAwareTaskIdentityFingerprintStrategy.9b7c47c56621_53c4bb9c6553')
DEBUG] Skipping insert of existing artifact: CacheKey(id=u'examples.src.java.HelloWorldClient.main-bin', hash=u'5e3111685874e202034a204a303cc4538cfb53b8-ResolvedJarAwareTaskIdentityFingerprintStrategy.9ee2d2aeba99_53c4bb9c6553')

                   Using cached artifacts for 2 targets.
11:45:58 00:02     [jvm-dep-check]
11:45:58 00:02   [resources]
11:45:58 00:02     [prepare]
11:45:58 00:02     [services]
11:45:58 00:02   [binary]
11:45:58 00:02     [binary-jvm-prep-command]
11:45:58 00:02       [jvm_prep_command]
11:45:58 00:02     [binary-prep-command]
11:45:58 00:02     [python-binary-create]
11:45:58 00:02     [jvm]
                   creating dist/hello-world-client.jar
11:45:58 00:02       [create-monolithic-jar]
11:45:58 00:02         [add-internal-classes]
11:45:58 00:02         [add-dependency-jars]
                         dumping org.slf4j:slf4j-simple:1.7.21::jar from /Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.slf4j/slf4j-simple/jars/slf4j-simple-1.7.21.jar
                         dumping org.slf4j:slf4j-api:1.7.21::jar from /Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.slf4j/slf4j-api/jars/slf4j-api-1.7.21.jar
                         dumping org.apache.thrift:libthrift:0.9.2::jar from /Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.apache.thrift/libthrift/jars/libthrift-0.9.2.jar
                         dumping org.apache.httpcomponents:httpclient:4.2.5::jar from /Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.apache.httpcomponents/httpclient/jars/httpclient-4.2.5.jar
                         dumping commons-codec:commons-codec:1.6::jar from /Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/commons-codec/commons-codec/jars/commons-codec-1.6.jar
                         dumping commons-logging:commons-logging:1.1.1::jar from /Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/commons-logging/commons-logging/jars/commons-logging-1.1.1.jar
                         dumping org.apache.httpcomponents:httpcore:4.2.4::jar from /Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.apache.httpcomponents/httpcore/jars/httpcore-4.2.4.jar
11:45:58 00:02         [cache].DEBUG] Performing a fetch using ivy.

11:45:58 00:02         [bootstrap-jar-tool]DEBUG] Executing: /usr/bin/java -Xmx256m -Dsun.io.useCanonCaches=false -Divy.cache.dir=/Users/leo/.ivy2/pants -cp ../../../.ivy2/pants/org.apache.ivy/ivy/jars/ivy-2.4.0.jar org.apache.ivy.Main -ivy /Users/leo/dev/repro/leo_liang/.pants.d/ivy/2bb7e56b3f66cee074293b0d2a3753cdb521bc83-IvyResolveFingerprintStrategy_53c4bb9c6553/fetch-ivy.xml -confs default -cachepath /Users/leo/dev/repro/leo_liang/.pants.d/ivy/2bb7e56b3f66cee074293b0d2a3753cdb521bc83-IvyResolveFingerprintStrategy_53c4bb9c6553/classpath.raw.tmp.736b8b66eb0640fcaefba74ca07ee828 args={'stderr': <pants.util.rwbuf.FileBackedRWBuf object at 0x10d3898d0>, 'stdout': <pants.util.rwbuf.FileBackedRWBuf object at 0x10d389890>} at cwd=/Users/leo/dev/repro/leo_liang
DEBUG] Moved ivy classfile file to /Users/leo/dev/repro/leo_liang/.pants.d/ivy/2bb7e56b3f66cee074293b0d2a3753cdb521bc83-IvyResolveFingerprintStrategy_53c4bb9c6553/classpath.raw
DEBUG] Using previous fetch.

11:45:58 00:02         [jar-tool]DEBUG] Nailgun ng_BinaryCreate_binary_jvm state: updated=True running=True fingerprint=82325ed9327e736c6932f55ba5731f95757fb8f5 new_fingerprint=c2fcb8d80e6569d0371e857044103c39f1ff899f distribution=/usr/bin/java new_distribution=/usr/bin/java
DEBUG] Found running nailgun server that needs updating, killing ng_BinaryCreate_binary_jvm
DEBUG] terminating ng_BinaryCreate_binary_jvm
DEBUG] sending signal 15 to pid 62654
DEBUG] successfully terminated pid 62654
DEBUG] purging metadata directory: /Users/leo/dev/repro/leo_liang/.pids/ng_BinaryCreate_binary_jvm
DEBUG] Spawning nailgun server ng_BinaryCreate_binary_jvm with fingerprint=c2fcb8d80e6569d0371e857044103c39f1ff899f, jvm_options=['-Xmx256m', u'-Dpants.buildroot=/Users/leo/dev/repro/leo_liang', u'-Dpants.nailgun.owner=/Users/leo/dev/repro/leo_liang/.pants.d/ng/BinaryCreate_binary_jvm', u'-Dpants.nailgun.fingerprint=c2fcb8d80e6569d0371e857044103c39f1ff899f'], classpath=['/Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/com.martiansoftware/nailgun-server/jars/nailgun-server-0.9.1.jar', '/Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.pantsbuild/jar-tool/jars/jar-tool-0.0.10.jar', '/Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/args4j/args4j/jars/args4j-2.32.jar', '/Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/com.google.code.findbugs/jsr305/jars/jsr305-3.0.0.jar', '/Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/com.google.guava/guava/jars/guava-18.0.jar', '/Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.pantsbuild/args4j/jars/args4j-0.0.9.jar']
DEBUG] purging metadata directory: /Users/leo/dev/repro/leo_liang/.pids/ng_BinaryCreate_binary_jvm
DEBUG] Executing: /usr/bin/java -Xmx256m -Dpants.buildroot=/Users/leo/dev/repro/leo_liang -Dpants.nailgun.owner=/Users/leo/dev/repro/leo_liang/.pants.d/ng/BinaryCreate_binary_jvm -Dpants.nailgun.fingerprint=c2fcb8d80e6569d0371e857044103c39f1ff899f -cp .pants.d/ivy/jars/com.martiansoftware/nailgun-server/jars/nailgun-server-0.9.1.jar:.pants.d/ivy/jars/org.pantsbuild/jar-tool/jars/jar-tool-0.0.10.jar:.pants.d/ivy/jars/args4j/args4j/jars/args4j-2.32.jar:.pants.d/ivy/jars/com.google.code.findbugs/jsr305/jars/jsr305-3.0.0.jar:.pants.d/ivy/jars/com.google.guava/guava/jars/guava-18.0.jar:.pants.d/ivy/jars/org.pantsbuild/args4j/jars/args4j-0.0.9.jar com.martiansoftware.nailgun.NGServer :0 args={'close_fds': True, 'stdin': <open file u'/dev/null', mode 'r' at 0x10d39d420>, 'stderr': <open file u'/Users/leo/dev/repro/leo_liang/.pants.d/ng/BinaryCreate_binary_jvm/stderr', mode 'w' at 0x10d39d540>, 'stdout': <open file u'/Users/leo/dev/repro/leo_liang/.pants.d/ng/BinaryCreate_binary_jvm/stdout', mode 'w' at 0x10d39d4b0>} at cwd=/Users/leo/dev/repro/leo_liang
DEBUG] Spawned nailgun server ng_BinaryCreate_binary_jvm with fingerprint=c2fcb8d80e6569d0371e857044103c39f1ff899f, pid=63009 port=50360
DEBUG] Verified new ng server is connectable at ('127.0.0.1', 50360)
DEBUG] Executing via NailgunClient(host=u'127.0.0.1', port=50360, workdir='/Users/leo/dev/repro/leo_liang'): /usr/bin/java -Xmx256m -cp /Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.pantsbuild/jar-tool/jars/jar-tool-0.0.10.jar:/Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/args4j/args4j/jars/args4j-2.32.jar:/Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/com.google.code.findbugs/jsr305/jars/jsr305-3.0.0.jar:/Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/com.google.guava/guava/jars/guava-18.0.jar:/Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.pantsbuild/args4j/jars/args4j-0.0.9.jar org.pantsbuild.tools.jar.Main -main=HelloWorldClient.HelloWorldClient -jars=/Users/leo/dev/repro/leo_liang/.pants.d/compile/zinc/252d64521cf9/examples.src.java.HelloWorldClient.main-bin/current/z.jar,/Users/leo/dev/repro/leo_liang/.pants.d/compile/zinc/252d64521cf9/.pants.d.gen.thrift.e300ff222c82.examples.src.thrift.hello_world_service.hello-world-service-java.current.examples.src.thrift.hello_world_service.hello-world-service-java/current/z.jar,/Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.slf4j/slf4j-simple/jars/slf4j-simple-1.7.21.jar,/Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.slf4j/slf4j-api/jars/slf4j-api-1.7.21.jar,/Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.apache.thrift/libthrift/jars/libthrift-0.9.2.jar,/Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.apache.httpcomponents/httpclient/jars/httpclient-4.2.5.jar,/Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/commons-codec/commons-codec/jars/commons-codec-1.6.jar,/Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/commons-logging/commons-logging/jars/commons-logging-1.1.1.jar,/Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.apache.httpcomponents/httpcore/jars/httpcore-4.2.4.jar -update=false -compress=true -default_action=SKIP -skip=^META-INF/[^/]+\.SF$,^META-INF/[^/]+\.DSA$,^META-INF/[^/]+\.RSA$,^META-INF/INDEX.LIST$ -policies=^META-INF/services/=CONCAT_TEXT /Users/leo/dev/repro/leo_liang/dist/hello-world-client.jar

11:45:59 00:03     [dup]
                     scanning org.slf4j:slf4j-simple:1.7.21::jar from /Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.slf4j/slf4j-simple/jars/slf4j-simple-1.7.21.jar
                     scanning org.slf4j:slf4j-api:1.7.21::jar from /Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.slf4j/slf4j-api/jars/slf4j-api-1.7.21.jar
                     scanning org.apache.thrift:libthrift:0.9.2::jar from /Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.apache.thrift/libthrift/jars/libthrift-0.9.2.jar
                     scanning org.apache.httpcomponents:httpclient:4.2.5::jar from /Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.apache.httpcomponents/httpclient/jars/httpclient-4.2.5.jar
                     scanning commons-codec:commons-codec:1.6::jar from /Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/commons-codec/commons-codec/jars/commons-codec-1.6.jar
                     scanning commons-logging:commons-logging:1.1.1::jar from /Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/commons-logging/commons-logging/jars/commons-logging-1.1.1.jar
                     scanning org.apache.httpcomponents:httpcore:4.2.4::jar from /Users/leo/dev/repro/leo_liang/.pants.d/ivy/jars/org.apache.httpcomponents/httpcore/jars/httpcore-4.2.4.jar
               Waiting for background workers to finish.
11:45:59 00:03   [complete]
               SUCCESS
               12:46 $ cd dist/
               ✔ ~/dev/repro/leo_liang/dist [master|✔]
               12:46 $ unzip hello-world-client.jar
               Archive:  hello-world-client.jar
                  creating: META-INF/
                 inflating: META-INF/MANIFEST.MF
                  creating: org/
                  creating: org/slf4j/
                  creating: org/slf4j/event/
                 inflating: org/slf4j/event/Level.class
                 inflating: org/slf4j/IMarkerFactory.class
                 inflating: org/slf4j/event/EventRecodingLogger.class
                  creating: org/slf4j/helpers/
                 inflating: org/slf4j/helpers/BasicMDCAdapter$1.class
                 inflating: org/slf4j/helpers/Util$ClassContextSecurityManager.class
                 inflating: org/slf4j/helpers/NOPLogger.class
                 inflating: org/slf4j/helpers/MessageFormatter.class
                 inflating: org/slf4j/ILoggerFactory.class
                 inflating: org/slf4j/helpers/BasicMarkerFactory.class
                 inflating: org/slf4j/helpers/MarkerIgnoringBase.class
                 inflating: org/slf4j/helpers/Util.class
                 inflating: org/slf4j/MDC$1.class
                 inflating: org/slf4j/LoggerFactory.class
                 inflating: org/slf4j/MarkerFactory.class
                 inflating: org/slf4j/helpers/NOPMDCAdapter.class
                 inflating: org/slf4j/Logger.class
                 inflating: org/slf4j/MDC$MDCCloseable.class
                  creating: META-INF/maven/
                  creating: META-INF/maven/org.slf4j/
                  creating: META-INF/maven/org.slf4j/slf4j-api/
                 inflating: META-INF/maven/org.slf4j/slf4j-api/pom.properties
                 inflating: org/slf4j/helpers/BasicMDCAdapter.class
                 inflating: org/slf4j/helpers/SubstituteLoggerFactory.class
                 inflating: org/slf4j/event/LoggingEvent.class
                 inflating: org/slf4j/helpers/NOPLoggerFactory.class
                 inflating: org/slf4j/helpers/SubstituteLogger.class
                  creating: org/slf4j/spi/
                 inflating: org/slf4j/spi/LocationAwareLogger.class
                 inflating: org/slf4j/spi/MarkerFactoryBinder.class
                 inflating: org/slf4j/spi/MDCAdapter.class
                 inflating: org/slf4j/event/EventConstants.class
                 inflating: org/slf4j/helpers/Util$1.class
                 inflating: org/slf4j/event/SubstituteLoggingEvent.class
                 inflating: org/slf4j/Marker.class
                 inflating: org/slf4j/helpers/NamedLoggerBase.class
                 inflating: META-INF/maven/org.slf4j/slf4j-api/pom.xml
                 inflating: org/slf4j/helpers/FormattingTuple.class
                 inflating: org/slf4j/MDC.class
                 inflating: org/slf4j/helpers/BasicMarker.class
                 inflating: org/slf4j/spi/LoggerFactoryBinder.class
                  creating: org/apache/
                  creating: org/apache/thrift/
                  creating: org/apache/thrift/transport/
                 inflating: org/apache/thrift/transport/TSocket.class
                 inflating: org/apache/thrift/TSerializer.class
                 inflating: org/apache/thrift/transport/TFramedTransport.class
                 inflating: META-INF/NOTICE.txt
                 inflating: org/apache/thrift/TBaseHelper$1.class
                 inflating: org/apache/thrift/transport/TMemoryBuffer.class
                 inflating: org/apache/thrift/TException.class
                  creating: org/apache/thrift/protocol/
                 inflating: org/apache/thrift/protocol/TType.class
                 inflating: org/apache/thrift/transport/AutoExpandingBufferReadTransport.class
                  creating: org/apache/thrift/async/
                 inflating: org/apache/thrift/async/TAsyncMethodCall$State.class
                  creating: org/apache/thrift/server/
                 inflating: org/apache/thrift/server/TServerEventHandler.class
                 inflating: org/apache/thrift/transport/TFramedTransport$Factory.class
                 inflating: org/apache/thrift/protocol/TBinaryProtocol$Factory.class
                 inflating: org/apache/thrift/server/THsHaServer.class
                 inflating: org/apache/thrift/transport/TServerSocket$ServerSocketTransportArgs.class
                 inflating: org/apache/thrift/transport/TFileTransport$TruncableBufferedInputStream.class
                 inflating: org/apache/thrift/protocol/TMultiplexedProtocol.class
                 inflating: org/apache/thrift/transport/TNonblockingTransport.class
                 inflating: org/apache/thrift/TByteArrayOutputStream.class
                 inflating: org/apache/thrift/transport/TTransportException.class
                 inflating: org/apache/thrift/server/TServer$Args.class
                 inflating: org/apache/thrift/transport/TTransportFactory.class
                 inflating: org/apache/thrift/protocol/TProtocolException.class
                 inflating: org/apache/thrift/TDeserializer.class
                 inflating: org/apache/thrift/transport/TNonblockingServerSocket$NonblockingAbstractServerSocketArgs.class
                 inflating: org/apache/thrift/TUnion$TUnionStandardSchemeFactory.class
                  creating: org/apache/thrift/meta_data/
                 inflating: org/apache/thrift/meta_data/MapMetaData.class
                 inflating: org/apache/thrift/meta_data/StructMetaData.class
                 inflating: org/apache/thrift/server/AbstractNonblockingServer$AbstractSelectThread.class
                 inflating: META-INF/LICENSE.txt
                 inflating: org/apache/thrift/async/TAsyncClient.class
                 inflating: org/apache/thrift/transport/TMemoryInputTransport.class
                 inflating: org/apache/thrift/server/TThreadedSelectorServer$SelectorThreadLoadBalancer.class
                 inflating: org/apache/thrift/server/TServlet$1.class
                 inflating: org/apache/thrift/transport/TNonblockingServerTransport.class
                 inflating: org/apache/thrift/TServiceClient.class
                 inflating: org/apache/thrift/protocol/TJSONProtocol$Factory.class
                 inflating: org/apache/thrift/async/TAsyncClientFactory.class
                 inflating: org/apache/thrift/meta_data/SetMetaData.class
                 inflating: org/apache/thrift/protocol/TBase64Utils.class
                 inflating: org/apache/thrift/server/TSimpleServer.class
                 inflating: org/apache/thrift/protocol/TSet.class
                 inflating: org/apache/thrift/transport/TFileTransport.class
                 inflating: org/apache/thrift/server/TServer$AbstractServerArgs.class
                 inflating: org/apache/thrift/transport/TIOStreamTransport.class
                 inflating: org/apache/thrift/TEnumHelper.class
                 inflating: org/apache/thrift/meta_data/FieldMetaData.class
                 inflating: org/apache/thrift/TBaseHelper.class
                 inflating: org/apache/thrift/async/AsyncMethodCallback.class
                 inflating: org/apache/thrift/TUnion$TUnionTupleSchemeFactory.class
                  creating: org/apache/thrift/scheme/
                 inflating: org/apache/thrift/scheme/IScheme.class
                 inflating: org/apache/thrift/transport/TFileTransport$TailPolicy.class
                 inflating: org/apache/thrift/protocol/TJSONProtocol$JSONPairContext.class
                 inflating: org/apache/thrift/server/ServerContext.class
                 inflating: org/apache/thrift/server/THsHaServer$Args.class
                 inflating: org/apache/thrift/transport/TTransport.class
                 inflating: org/apache/thrift/scheme/StandardScheme.class
                 inflating: org/apache/thrift/server/TThreadedSelectorServer$Args.class
                 inflating: org/apache/thrift/transport/TFileProcessor.class
                 inflating: org/apache/thrift/transport/TSaslClientTransport.class
                 inflating: org/apache/thrift/TNonblockingMultiFetchClient$MultiFetch.class
                 inflating: org/apache/thrift/async/TAsyncMethodCall$1.class
                 inflating: org/apache/thrift/TNonblockingMultiFetchStats.class
                 inflating: org/apache/thrift/TEnum.class
                 inflating: org/apache/thrift/async/TAsyncClientManager$1.class
                 inflating: org/apache/thrift/transport/TSimpleFileTransport.class
                 inflating: org/apache/thrift/server/TThreadedSelectorServer$AcceptThread.class
                 inflating: org/apache/thrift/protocol/TJSONProtocol$LookaheadReader.class
                 inflating: org/apache/thrift/protocol/TProtocolUtil.class
                 inflating: org/apache/thrift/server/AbstractNonblockingServer$AbstractNonblockingServerArgs.class
                 inflating: org/apache/thrift/transport/TFastFramedTransport$Factory.class
                 inflating: org/apache/thrift/scheme/SchemeFactory.class
                 inflating: org/apache/thrift/transport/TSSLTransportFactory$TSSLTransportParameters.class
                 inflating: org/apache/thrift/server/Invocation.class
                 inflating: org/apache/thrift/meta_data/ListMetaData.class
                 inflating: org/apache/thrift/protocol/TBinaryProtocol.class
                 inflating: org/apache/thrift/protocol/TProtocolDecorator.class
                 inflating: org/apache/thrift/async/TAsyncClientManager$SelectThread.class
                 inflating: org/apache/thrift/EncodingUtils.class
                 inflating: org/apache/thrift/protocol/TList.class
                 inflating: org/apache/thrift/protocol/TSimpleJSONProtocol$ListContext.class
                 inflating: org/apache/thrift/transport/TSaslServerTransport$Factory.class
                 inflating: org/apache/thrift/protocol/TStruct.class
                 inflating: org/apache/thrift/transport/TFastFramedTransport.class
                 inflating: org/apache/thrift/server/AbstractNonblockingServer$FrameBufferState.class
                 inflating: org/apache/thrift/transport/TSaslTransportException.class
                 inflating: org/apache/thrift/server/TExtensibleServlet$1.class
                 inflating: org/apache/thrift/transport/TFileTransport$ChunkState.class
                 inflating: org/apache/thrift/server/TServlet.class
                 inflating: org/apache/thrift/transport/TSaslServerTransport.class
                 inflating: org/apache/thrift/transport/TSaslTransport$NegotiationStatus.class
                 inflating: org/apache/thrift/AsyncProcessFunction.class
                 inflating: org/apache/thrift/protocol/TSimpleJSONProtocol.class
                 inflating: org/apache/thrift/TUnion.class
                 inflating: org/apache/thrift/protocol/TSimpleJSONProtocol$Context.class
                 inflating: org/apache/thrift/server/AbstractNonblockingServer$FrameBuffer.class
                 inflating: org/apache/thrift/transport/THttpClient.class
                 inflating: org/apache/thrift/server/TThreadedSelectorServer$Args$AcceptPolicy.class
                 inflating: org/apache/thrift/protocol/TCompactProtocol.class
                 inflating: org/apache/thrift/protocol/TJSONProtocol$JSONBaseContext.class
                 inflating: org/apache/thrift/transport/TStandardFile.class
                 inflating: org/apache/thrift/protocol/TTupleProtocol$Factory.class
                 inflating: org/apache/thrift/TMultiplexedProcessor$StoredMessageProtocol.class
                 inflating: org/apache/thrift/ShortStack.class
                 inflating: org/apache/thrift/meta_data/EnumMetaData.class
                 inflating: org/apache/thrift/transport/AutoExpandingBuffer.class
                 inflating: org/apache/thrift/protocol/TField.class
                 inflating: org/apache/thrift/protocol/TMessageType.class
                 inflating: org/apache/thrift/TApplicationException.class
                 inflating: org/apache/thrift/protocol/TMessage.class
                 inflating: org/apache/thrift/transport/TNonblockingServerSocket.class
                 inflating: org/apache/thrift/server/TExtensibleServlet.class
                 inflating: org/apache/thrift/transport/TNonblockingSocket.class
                 inflating: org/apache/thrift/transport/TSeekableFile.class
                 inflating: org/apache/thrift/protocol/TMap.class
                 inflating: org/apache/thrift/transport/TServerTransport.class
                 inflating: org/apache/thrift/TBaseAsyncProcessor.class
                 inflating: org/apache/thrift/server/TThreadedSelectorServer.class
                 inflating: org/apache/thrift/server/AbstractNonblockingServer$AsyncFrameBuffer.class
                 inflating: org/apache/thrift/TProcessor.class
                 inflating: org/apache/thrift/TProcessorFactory.class
                 inflating: org/apache/thrift/transport/TServerSocket.class
                 inflating: org/apache/thrift/protocol/TSimpleJSONProtocol$Factory.class
                 inflating: org/apache/thrift/TNonblockingMultiFetchClient.class
                 inflating: org/apache/thrift/transport/TSSLTransportFactory.class
                 inflating: org/apache/thrift/async/TAsyncClientManager$TAsyncMethodCallTimeoutComparator.class
                 inflating: org/apache/thrift/protocol/TProtocol.class
                 inflating: org/apache/thrift/server/TNonblockingServer$SelectAcceptThread.class
                 inflating: org/apache/thrift/async/TAsyncMethodCall.class
                 inflating: org/apache/thrift/TFieldIdEnum.class
                 inflating: org/apache/thrift/protocol/TCompactProtocol$Types.class
                 inflating: org/apache/thrift/TFieldRequirementType.class
                 inflating: org/apache/thrift/protocol/TSimpleJSONProtocol$MapContext.class
                 inflating: org/apache/thrift/transport/TSaslTransport$SaslRole.class
                 inflating: org/apache/thrift/server/TThreadPoolServer$1.class
                 inflating: org/apache/thrift/transport/AutoExpandingBufferWriteTransport.class
                 inflating: org/apache/thrift/transport/TFileTransport$Event.class
                 inflating: org/apache/thrift/server/TThreadPoolServer$Args.class
                 inflating: org/apache/thrift/async/TAsyncClientManager.class
                 inflating: org/apache/thrift/server/AbstractNonblockingServer.class
                 inflating: org/apache/thrift/server/TThreadedSelectorServer$SelectorThread.class
                 inflating: org/apache/thrift/TBaseProcessor.class
                 inflating: org/apache/thrift/TUnion$TUnionStandardScheme.class
                 inflating: org/apache/thrift/protocol/TTupleProtocol.class
                 inflating: org/apache/thrift/transport/TSaslServerTransport$1.class
                 inflating: org/apache/thrift/TBaseHelper$NestedStructureComparator.class
                 inflating: org/apache/thrift/protocol/TJSONProtocol.class
                 inflating: org/apache/thrift/server/TNonblockingServer.class
                 inflating: org/apache/thrift/scheme/TupleScheme.class
                 inflating: org/apache/thrift/server/TThreadPoolServer$WorkerProcess.class
                 inflating: org/apache/thrift/TMultiplexedProcessor.class
                 inflating: org/apache/thrift/TBase.class
                 inflating: org/apache/thrift/TNonblockingMultiFetchClient$1.class
                 inflating: org/apache/thrift/server/TServer.class
                 inflating: org/apache/thrift/transport/TServerTransport$AbstractServerTransportArgs.class
                 inflating: org/apache/thrift/TUnion$1.class
                 inflating: org/apache/thrift/TUnion$TUnionTupleScheme.class
                 inflating: org/apache/thrift/meta_data/FieldValueMetaData.class
                 inflating: org/apache/thrift/ProcessFunction.class
                 inflating: org/apache/thrift/transport/TSaslTransport$SaslResponse.class
                 inflating: org/apache/thrift/transport/TSaslTransport.class
                 inflating: org/apache/thrift/server/TThreadedSelectorServer$AcceptThread$1.class
                 inflating: org/apache/thrift/transport/TSaslServerTransport$TSaslServerDefinition.class
                 inflating: org/apache/thrift/transport/THttpClient$Factory.class
                 inflating: org/apache/thrift/protocol/TCompactProtocol$Factory.class
                 inflating: org/apache/thrift/protocol/TSimpleJSONProtocol$StructContext.class
                 inflating: org/apache/thrift/protocol/TSimpleJSONProtocol$CollectionMapKeyException.class
                 inflating: org/apache/thrift/server/TThreadPoolServer.class
                 inflating: org/apache/thrift/protocol/TJSONProtocol$JSONListContext.class
                 inflating: org/apache/thrift/TServiceClientFactory.class
                 inflating: org/apache/thrift/protocol/TProtocolFactory.class
                 inflating: org/apache/thrift/server/TNonblockingServer$Args.class
                 inflating: org/apache/thrift/transport/TSaslTransport$SaslParticipant.class
                  creating: HelloWorldClient/
                extracting: HelloWorldClient/HelloWorldClient.class
                  creating: compile_classpath/
                extracting: compile_classpath/examples.src.java.HelloWorldClient.main-bin.txt
                  creating: hello_world_service/
                extracting: hello_world_service/HelloWorldService$AsyncIface.class
                extracting: hello_world_service/HelloWorldService$ping_result$ping_resultTupleScheme.class
                extracting: hello_world_service/HelloWorldService$ping_args.class
                extracting: hello_world_service/HelloWorldService$Iface.class
                extracting: hello_world_service/HelloWorldService$ping_args$ping_argsTupleSchemeFactory.class
                extracting: hello_world_service/HelloWorldService$AsyncClient$Factory.class
                extracting: hello_world_service/HelloWorldService$ping_args$_Fields.class
                extracting: hello_world_service/HelloWorldService$ping_args$ping_argsStandardSchemeFactory.class
                extracting: hello_world_service/HelloWorldService$AsyncClient.class
                extracting: hello_world_service/HelloWorldService$ping_result$ping_resultStandardSchemeFactory.class
                extracting: hello_world_service/HelloWorldService$AsyncProcessor$ping$1.class
                extracting: hello_world_service/HelloWorldService$ping_args$ping_argsTupleScheme.class
                extracting: hello_world_service/HelloWorldService$ping_result$ping_resultTupleSchemeFactory.class
                extracting: hello_world_service/HelloWorldService.class
                extracting: hello_world_service/HelloWorldService$AsyncClient$ping_call.class
                extracting: hello_world_service/HelloWorldService$AsyncProcessor$ping.class
                extracting: hello_world_service/HelloWorldService$ping_args$ping_argsStandardScheme.class
                extracting: hello_world_service/HelloWorldService$ping_result$_Fields.class
                extracting: compile_classpath/.pants.d.gen.thrift.252d64521cf9.examples.src.thrift.hello_world_service.hello-world-service-java.current.examples.src.thrift.hello_world_service.hello-world-service-java.txt
                extracting: hello_world_service/HelloWorldService$Processor$ping.class
                extracting: hello_world_service/HelloWorldService$ping_result$ping_resultStandardScheme.class
                extracting: hello_world_service/HelloWorldService$1.class
                extracting: hello_world_service/HelloWorldService$AsyncProcessor.class
                extracting: hello_world_service/HelloWorldService$ping_result.class
                extracting: hello_world_service/HelloWorldService$Client.class
                extracting: hello_world_service/HelloWorldService$Client$Factory.class
                extracting: hello_world_service/HelloWorldService$Processor.class
                  creating: org/slf4j/impl/
                 inflating: org/slf4j/impl/StaticMDCBinder.class
                 inflating: org/slf4j/impl/SimpleLogger.class
                 inflating: org/slf4j/impl/StaticLoggerBinder.class
                 inflating: org/slf4j/impl/SimpleLoggerFactory.class
                  creating: META-INF/maven/org.slf4j/slf4j-simple/
                 inflating: META-INF/maven/org.slf4j/slf4j-simple/pom.properties
                 inflating: META-INF/maven/org.slf4j/slf4j-simple/pom.xml
                 inflating: org/slf4j/impl/SimpleLogger$1.class
                 inflating: org/slf4j/impl/StaticMarkerBinder.class
                  creating: org/apache/commons/
                  creating: org/apache/commons/codec/
                  creating: org/apache/commons/codec/language/
                  creating: org/apache/commons/codec/language/bm/
                 inflating: org/apache/commons/codec/language/bm/Rule$RPattern.class
                 inflating: org/apache/commons/codec/language/bm/ash_approx_hungarian.txt
                 inflating: org/apache/commons/codec/language/bm/lang.txt
                 inflating: org/apache/commons/codec/language/Metaphone.class
                 inflating: org/apache/commons/codec/BinaryEncoder.class
                 inflating: org/apache/commons/codec/language/DoubleMetaphone$DoubleMetaphoneResult.class
                 inflating: org/apache/commons/codec/language/bm/Languages$LanguageSet.class
                 inflating: org/apache/commons/codec/language/bm/NameType.class
                 inflating: org/apache/commons/codec/language/bm/sep_approx_any.txt
                  creating: org/apache/commons/codec/binary/
                 inflating: org/apache/commons/codec/binary/BaseNCodecInputStream.class
                 inflating: org/apache/commons/codec/language/AbstractCaverphone.class
                 inflating: org/apache/commons/codec/language/bm/Rule$1.class
                 inflating: org/apache/commons/codec/language/bm/gen_exact_approx_common.txt
                 inflating: org/apache/commons/codec/EncoderException.class
                 inflating: org/apache/commons/codec/language/bm/sep_approx_french.txt
                 inflating: org/apache/commons/codec/language/bm/gen_approx_hebrew.txt
                 inflating: org/apache/commons/codec/language/bm/ash_rules_cyrillic.txt
                 inflating: org/apache/commons/codec/language/bm/ash_rules_english.txt
                 inflating: org/apache/commons/codec/language/bm/sep_languages.txt
                 inflating: org/apache/commons/codec/language/bm/gen_rules_italian.txt
                 inflating: org/apache/commons/codec/language/bm/sep_approx_italian.txt
                 inflating: org/apache/commons/codec/language/bm/gen_approx_cyrillic.txt
                 inflating: org/apache/commons/codec/language/bm/gen_rules_spanish.txt
                 inflating: org/apache/commons/codec/language/Soundex.class
                 inflating: org/apache/commons/codec/language/bm/gen_approx_italian.txt
                 inflating: org/apache/commons/codec/language/bm/gen_exact_dutch.txt
                 inflating: org/apache/commons/codec/language/bm/gen_exact_romanian.txt
                 inflating: org/apache/commons/codec/language/bm/ash_approx_hebrew.txt
                 inflating: org/apache/commons/codec/language/bm/Rule$7.class
                 inflating: org/apache/commons/codec/CharEncoding.class
                 inflating: org/apache/commons/codec/language/bm/gen_rules_dutch.txt
                 inflating: org/apache/commons/codec/language/bm/gen_approx_hungarian.txt
                 inflating: org/apache/commons/codec/language/bm/gen_exact_hungarian.txt
                 inflating: org/apache/commons/codec/language/bm/ash_approx_any.txt
                 inflating: org/apache/commons/codec/language/bm/gen_exact_czech.txt
                 inflating: org/apache/commons/codec/language/bm/Rule.class
                 inflating: org/apache/commons/codec/language/bm/sep_exact_hebrew.txt
                 inflating: org/apache/commons/codec/language/bm/RuleType.class
                 inflating: org/apache/commons/codec/language/bm/Rule$Phoneme.class
                 inflating: org/apache/commons/codec/language/bm/sep_exact_portuguese.txt
                 inflating: org/apache/commons/codec/language/bm/gen_approx_dutch.txt
                  creating: META-INF/maven/commons-codec/
                  creating: META-INF/maven/commons-codec/commons-codec/
                 inflating: META-INF/maven/commons-codec/commons-codec/pom.xml
                 inflating: org/apache/commons/codec/language/bm/gen_rules_hungarian.txt
                 inflating: org/apache/commons/codec/language/bm/sep_rules_hebrew.txt
                 inflating: org/apache/commons/codec/language/bm/ash_exact_french.txt
                 inflating: org/apache/commons/codec/language/bm/Rule$9.class
                 inflating: org/apache/commons/codec/StringEncoderComparator.class
                 inflating: org/apache/commons/codec/language/bm/ash_exact_any.txt
                 inflating: org/apache/commons/codec/language/SoundexUtils.class
                 inflating: org/apache/commons/codec/language/bm/ash_rules_any.txt
                 inflating: org/apache/commons/codec/language/bm/ash_rules_hungarian.txt
                 inflating: org/apache/commons/codec/language/bm/ash_rules_russian.txt
                 inflating: org/apache/commons/codec/language/bm/gen_approx_romanian.txt
                 inflating: org/apache/commons/codec/language/bm/ash_exact_cyrillic.txt
                 inflating: org/apache/commons/codec/language/bm/gen_exact_russian.txt
                 inflating: org/apache/commons/codec/binary/Base64InputStream.class
                 inflating: org/apache/commons/codec/language/bm/gen_rules_russian.txt
                 inflating: org/apache/commons/codec/language/bm/Languages$1.class
                 inflating: org/apache/commons/codec/language/bm/ash_exact_german.txt
                 inflating: org/apache/commons/codec/language/ColognePhonetic$CologneInputBuffer.class
                 inflating: org/apache/commons/codec/language/bm/sep_approx_hebrew.txt
                 inflating: org/apache/commons/codec/binary/Base64OutputStream.class
                 inflating: org/apache/commons/codec/language/bm/gen_approx_portuguese.txt
                 inflating: org/apache/commons/codec/language/bm/ash_hebrew_common.txt
                 inflating: org/apache/commons/codec/language/bm/Rule$Phoneme$1.class
                 inflating: org/apache/commons/codec/language/bm/ash_approx_german.txt
                 inflating: org/apache/commons/codec/language/bm/ash_approx_common.txt
                 inflating: org/apache/commons/codec/language/bm/ash_exact_hungarian.txt
                 inflating: org/apache/commons/codec/binary/Base64.class
                 inflating: META-INF/maven/commons-codec/commons-codec/pom.properties
                 inflating: org/apache/commons/codec/binary/Base32InputStream.class
                 inflating: org/apache/commons/codec/language/bm/gen_exact_italian.txt
                 inflating: org/apache/commons/codec/language/bm/gen_rules_romanian.txt
                 inflating: org/apache/commons/codec/binary/BinaryCodec.class
                 inflating: org/apache/commons/codec/language/bm/Languages$SomeLanguages.class
                 inflating: org/apache/commons/codec/language/bm/gen_rules_german.txt
                 inflating: org/apache/commons/codec/language/bm/gen_exact_greeklatin.txt
                 inflating: org/apache/commons/codec/Decoder.class
                 inflating: org/apache/commons/codec/language/bm/gen_exact_arabic.txt
                 inflating: org/apache/commons/codec/language/bm/gen_rules_czech.txt
                 inflating: org/apache/commons/codec/binary/StringUtils.class
                 inflating: org/apache/commons/codec/language/bm/gen_rules_arabic.txt
                 inflating: org/apache/commons/codec/language/bm/sep_rules_spanish.txt
                 inflating: org/apache/commons/codec/language/bm/gen_approx_french.txt
                 inflating: org/apache/commons/codec/language/bm/Lang$1.class
                 inflating: org/apache/commons/codec/language/bm/sep_rules_italian.txt
                 inflating: org/apache/commons/codec/binary/BaseNCodecOutputStream.class
                 inflating: org/apache/commons/codec/language/bm/gen_exact_turkish.txt
                  creating: org/apache/commons/codec/net/
                 inflating: org/apache/commons/codec/net/RFC1522Codec.class
                 inflating: org/apache/commons/codec/language/bm/ash_approx_polish.txt
                 inflating: org/apache/commons/codec/language/bm/gen_approx_common.txt
                 inflating: org/apache/commons/codec/language/bm/gen_rules_english.txt
                 inflating: org/apache/commons/codec/net/QuotedPrintableCodec.class
                 inflating: org/apache/commons/codec/BinaryDecoder.class
                 inflating: org/apache/commons/codec/language/bm/gen_approx_russian.txt
                 inflating: org/apache/commons/codec/language/bm/gen_rules_portuguese.txt
                 inflating: org/apache/commons/codec/language/bm/sep_hebrew_common.txt
                 inflating: org/apache/commons/codec/language/bm/sep_rules_any.txt
                 inflating: org/apache/commons/codec/language/bm/gen_exact_portuguese.txt
                 inflating: org/apache/commons/codec/language/bm/sep_approx_spanish.txt
                 inflating: org/apache/commons/codec/language/bm/PhoneticEngine$PhonemeBuilder.class
                 inflating: org/apache/commons/codec/language/bm/gen_exact_hebrew.txt
                 inflating: org/apache/commons/codec/language/bm/ash_approx_english.txt
                 inflating: org/apache/commons/codec/language/bm/gen_approx_spanish.txt
                 inflating: org/apache/commons/codec/language/Caverphone1.class
                 inflating: org/apache/commons/codec/language/bm/ash_exact_romanian.txt
                 inflating: org/apache/commons/codec/language/bm/gen_languages.txt
                 inflating: org/apache/commons/codec/language/bm/gen_exact_common.txt
                 inflating: org/apache/commons/codec/language/bm/gen_rules_hebrew.txt
                 inflating: org/apache/commons/codec/language/bm/PhoneticEngine$RulesApplication.class
                 inflating: org/apache/commons/codec/language/bm/Rule$6.class
                 inflating: org/apache/commons/codec/StringDecoder.class
                 inflating: org/apache/commons/codec/language/bm/ResourceConstants.class
                 inflating: org/apache/commons/codec/binary/BaseNCodec.class
                 inflating: org/apache/commons/codec/language/bm/ash_exact_hebrew.txt
                 inflating: org/apache/commons/codec/language/bm/gen_hebrew_common.txt
                 inflating: org/apache/commons/codec/language/bm/ash_exact_english.txt
                 inflating: org/apache/commons/codec/language/bm/Rule$PhonemeExpr.class
                 inflating: org/apache/commons/codec/language/ColognePhonetic$CologneOutputBuffer.class
                 inflating: org/apache/commons/codec/language/bm/gen_rules_greeklatin.txt
                 inflating: org/apache/commons/codec/language/bm/ash_languages.txt
                 inflating: org/apache/commons/codec/language/bm/Rule$5.class
                 inflating: org/apache/commons/codec/language/bm/ash_approx_french.txt
                 inflating: org/apache/commons/codec/language/bm/gen_approx_english.txt
                 inflating: org/apache/commons/codec/language/ColognePhonetic.class
                 inflating: org/apache/commons/codec/language/bm/ash_exact_polish.txt
                 inflating: org/apache/commons/codec/language/bm/sep_approx_portuguese.txt
                 inflating: org/apache/commons/codec/language/bm/PhoneticEngine.class
                 inflating: org/apache/commons/codec/binary/Hex.class
                 inflating: org/apache/commons/codec/language/Caverphone2.class
                 inflating: org/apache/commons/codec/language/bm/sep_exact_french.txt
                 inflating: org/apache/commons/codec/language/bm/ash_exact_approx_common.txt
                 inflating: org/apache/commons/codec/language/bm/Languages$2.class
                 inflating: org/apache/commons/codec/language/bm/Rule$8.class
                 inflating: org/apache/commons/codec/language/bm/sep_exact_spanish.txt
                 inflating: org/apache/commons/codec/binary/Base32OutputStream.class
                 inflating: org/apache/commons/codec/language/bm/gen_rules_greek.txt
                 inflating: org/apache/commons/codec/language/bm/Lang.class
                 inflating: org/apache/commons/codec/language/bm/gen_rules_turkish.txt
                 inflating: org/apache/commons/codec/language/bm/gen_rules_cyrillic.txt
                 inflating: org/apache/commons/codec/language/bm/ash_rules_hebrew.txt
                 inflating: org/apache/commons/codec/language/bm/gen_exact_german.txt
                 inflating: org/apache/commons/codec/language/bm/PhoneticEngine$2.class
                 inflating: org/apache/commons/codec/language/bm/gen_rules_french.txt
                 inflating: org/apache/commons/codec/language/bm/PhoneticEngine$1.class
                 inflating: org/apache/commons/codec/language/bm/gen_exact_cyrillic.txt
                 inflating: org/apache/commons/codec/net/BCodec.class
                 inflating: org/apache/commons/codec/language/bm/gen_exact_english.txt
                 inflating: org/apache/commons/codec/net/Utils.class
                 inflating: org/apache/commons/codec/language/bm/ash_rules_polish.txt
                 inflating: org/apache/commons/codec/language/bm/gen_approx_any.txt
                 inflating: org/apache/commons/codec/language/bm/sep_exact_approx_common.txt
                 inflating: org/apache/commons/codec/language/bm/gen_rules_polish.txt
                 inflating: org/apache/commons/codec/language/bm/Rule$PhonemeList.class
                 inflating: org/apache/commons/codec/Encoder.class
                 inflating: org/apache/commons/codec/language/bm/ash_approx_cyrillic.txt
                 inflating: org/apache/commons/codec/language/bm/sep_approx_common.txt
                 inflating: org/apache/commons/codec/language/bm/sep_rules_french.txt
                 inflating: org/apache/commons/codec/language/bm/ash_approx_spanish.txt
                 inflating: org/apache/commons/codec/language/bm/gen_exact_french.txt
                 inflating: org/apache/commons/codec/language/bm/Rule$3.class
                 inflating: org/apache/commons/codec/language/bm/Rule$10.class
                 inflating: org/apache/commons/codec/language/bm/Languages.class
                 inflating: org/apache/commons/codec/language/Caverphone.class
                 inflating: org/apache/commons/codec/language/bm/sep_exact_any.txt
                 inflating: org/apache/commons/codec/language/bm/BeiderMorseEncoder.class
                 inflating: org/apache/commons/codec/language/bm/ash_rules_french.txt
                 inflating: org/apache/commons/codec/net/URLCodec.class
                 inflating: org/apache/commons/codec/language/bm/ash_approx_romanian.txt
                 inflating: org/apache/commons/codec/language/bm/sep_exact_common.txt
                 inflating: org/apache/commons/codec/language/bm/gen_approx_german.txt
                 inflating: org/apache/commons/codec/language/bm/gen_approx_turkish.txt
                 inflating: org/apache/commons/codec/language/ColognePhonetic$CologneBuffer.class
                 inflating: org/apache/commons/codec/language/DoubleMetaphone.class
                 inflating: org/apache/commons/codec/language/bm/ash_rules_spanish.txt
                  creating: org/apache/commons/codec/digest/
                 inflating: org/apache/commons/codec/digest/DigestUtils.class
                 inflating: org/apache/commons/codec/language/bm/ash_rules_german.txt
                 inflating: org/apache/commons/codec/binary/Base32.class
                 inflating: org/apache/commons/codec/language/bm/Rule$2.class
                 inflating: org/apache/commons/codec/language/bm/gen_approx_czech.txt
                 inflating: org/apache/commons/codec/language/bm/gen_rules_any.txt
                 inflating: org/apache/commons/codec/language/bm/gen_approx_greeklatin.txt
                 inflating: org/apache/commons/codec/DecoderException.class
                 inflating: org/apache/commons/codec/net/QCodec.class
                 inflating: org/apache/commons/codec/language/bm/gen_exact_any.txt
                 inflating: org/apache/commons/codec/language/bm/ash_exact_common.txt
                 inflating: org/apache/commons/codec/StringEncoder.class
                 inflating: org/apache/commons/codec/language/bm/gen_approx_arabic.txt
                 inflating: org/apache/commons/codec/language/bm/gen_exact_greek.txt
                 inflating: org/apache/commons/codec/language/bm/Lang$LangRule.class
                 inflating: org/apache/commons/codec/language/bm/gen_exact_spanish.txt
                 inflating: org/apache/commons/codec/language/bm/gen_approx_greek.txt
                 inflating: org/apache/commons/codec/language/bm/ash_rules_romanian.txt
                 inflating: org/apache/commons/codec/language/bm/gen_exact_polish.txt
                 inflating: org/apache/commons/codec/language/bm/gen_approx_polish.txt
                 inflating: org/apache/commons/codec/language/RefinedSoundex.class
                 inflating: org/apache/commons/codec/language/bm/ash_exact_spanish.txt
                 inflating: org/apache/commons/codec/language/bm/sep_rules_portuguese.txt
                 inflating: org/apache/commons/codec/language/bm/ash_approx_russian.txt
                 inflating: org/apache/commons/codec/language/bm/ash_exact_russian.txt
                 inflating: org/apache/commons/codec/language/bm/sep_exact_italian.txt
                 inflating: org/apache/commons/codec/language/bm/Rule$4.class
                  creating: org/apache/http/
                  creating: org/apache/http/io/
                 inflating: org/apache/http/io/HttpMessageParser.class
                  creating: org/apache/http/entity/
                 inflating: org/apache/http/entity/ContentType.class
                 inflating: org/apache/http/HeaderElement.class
                 inflating: org/apache/http/HttpConnection.class
                  creating: org/apache/http/protocol/
                 inflating: org/apache/http/protocol/HttpContext.class
                 inflating: org/apache/http/protocol/HttpRequestHandler.class
                  creating: org/apache/http/impl/
                  creating: org/apache/http/impl/entity/
                 inflating: org/apache/http/impl/entity/DisallowIdentityContentLengthStrategy.class
                 inflating: org/apache/http/MalformedChunkCodingException.class
                 inflating: org/apache/http/entity/FileEntity.class
                  creating: org/apache/http/message/
                 inflating: org/apache/http/message/BasicHeaderElement.class
                  creating: org/apache/http/pool/
                 inflating: org/apache/http/pool/PoolStats.class
                 inflating: org/apache/http/io/EofSensor.class
                 inflating: org/apache/http/HttpResponseInterceptor.class
                  creating: org/apache/http/params/
                 inflating: org/apache/http/params/HttpConnectionParamBean.class
                 inflating: org/apache/http/protocol/ResponseDate.class
                 inflating: org/apache/http/protocol/RequestDate.class
                 inflating: org/apache/http/entity/BasicHttpEntity.class
                 inflating: org/apache/http/TokenIterator.class
                  creating: org/apache/http/util/
                 inflating: org/apache/http/util/ByteArrayBuffer.class
                 inflating: org/apache/http/params/HttpConnectionParams.class
                 inflating: org/apache/http/ConnectionReuseStrategy.class
                 inflating: org/apache/http/message/BasicHttpRequest.class
                 inflating: org/apache/http/HttpResponseFactory.class
                  creating: org/apache/http/impl/io/
                 inflating: org/apache/http/impl/io/IdentityOutputStream.class
                 inflating: org/apache/http/protocol/HttpRequestInterceptorList.class
                 inflating: org/apache/http/protocol/HttpRequestHandlerResolver.class
                 inflating: org/apache/http/protocol/HttpRequestHandlerRegistry.class
                 inflating: org/apache/http/impl/SocketHttpClientConnection.class
                 inflating: org/apache/http/pool/AbstractConnPool$2.class
                  creating: org/apache/http/impl/pool/
                 inflating: org/apache/http/impl/pool/BasicPoolEntry.class
                 inflating: org/apache/http/protocol/ResponseContent.class
                 inflating: org/apache/http/io/HttpMessageWriter.class
                 inflating: org/apache/http/protocol/SyncBasicHttpContext.class
                 inflating: org/apache/http/util/EncodingUtils.class
                 inflating: org/apache/http/protocol/RequestConnControl.class
                  creating: META-INF/maven/org.apache.httpcomponents/
                  creating: META-INF/maven/org.apache.httpcomponents/httpcore/
                 inflating: META-INF/maven/org.apache.httpcomponents/httpcore/pom.properties
                 inflating: org/apache/http/impl/entity/EntityDeserializer.class
                 inflating: org/apache/http/HttpRequest.class
                 inflating: org/apache/http/message/BasicLineFormatter.class
                 inflating: org/apache/http/impl/io/SocketInputBuffer.class
                 inflating: org/apache/http/HttpMessage.class
                 inflating: org/apache/http/ProtocolVersion.class
                 inflating: org/apache/http/impl/io/AbstractSessionInputBuffer.class
                 inflating: org/apache/http/pool/PoolEntryFuture.class
                 inflating: org/apache/http/HeaderIterator.class
                 inflating: META-INF/maven/org.apache.httpcomponents/httpcore/pom.xml
                 inflating: org/apache/http/message/AbstractHttpMessage.class
                 inflating: org/apache/http/params/HttpProtocolParamBean.class
                 inflating: org/apache/http/message/BasicLineParser.class
                 inflating: org/apache/http/impl/entity/LaxContentLengthStrategy.class
                 inflating: org/apache/http/message/HeaderGroup.class
                 inflating: org/apache/http/message/BasicStatusLine.class
                 inflating: org/apache/http/io/BufferInfo.class
                 inflating: org/apache/http/impl/SocketHttpServerConnection.class
                 inflating: org/apache/http/entity/ByteArrayEntity.class
                 inflating: org/apache/http/impl/io/HttpTransportMetricsImpl.class
                 inflating: org/apache/http/impl/pool/BasicConnFactory.class
                 inflating: org/apache/http/message/BasicHeaderIterator.class
                 inflating: org/apache/http/FormattedHeader.class
                 inflating: org/apache/http/impl/entity/EntitySerializer.class
                 inflating: org/apache/http/Header.class
                 inflating: org/apache/http/impl/io/SocketOutputBuffer.class
                 inflating: org/apache/http/impl/DefaultHttpResponseFactory.class
                 inflating: org/apache/http/params/AbstractHttpParams.class
                 inflating: org/apache/http/entity/InputStreamEntity.class
                  creating: org/apache/http/annotation/
                 inflating: org/apache/http/annotation/Immutable.class
                 inflating: org/apache/http/StatusLine.class
                 inflating: org/apache/http/params/CoreProtocolPNames.class
                 inflating: org/apache/http/protocol/RequestContent.class
                 inflating: org/apache/http/protocol/HTTP.class
                 inflating: org/apache/http/protocol/ResponseConnControl.class
                 inflating: org/apache/http/impl/io/ContentLengthOutputStream.class
                 inflating: org/apache/http/impl/io/AbstractMessageParser.class
                 inflating: org/apache/http/HttpException.class
                 inflating: org/apache/http/pool/AbstractConnPool.class
                 inflating: org/apache/http/message/BasicListHeaderIterator.class
                 inflating: org/apache/http/impl/io/ChunkedOutputStream.class
                 inflating: org/apache/http/impl/pool/BasicConnPool.class
                 inflating: org/apache/http/pool/RouteSpecificPool.class
                 inflating: org/apache/http/impl/AbstractHttpClientConnection.class
                 inflating: org/apache/http/message/BasicHeader.class
                 inflating: org/apache/http/params/HttpParamsNames.class
                 inflating: org/apache/http/message/HeaderValueFormatter.class
                 inflating: org/apache/http/MethodNotSupportedException.class
                 inflating: org/apache/http/protocol/UriPatternMatcher.class
                 inflating: org/apache/http/pool/PoolEntry.class
                 inflating: org/apache/http/entity/SerializableEntity.class
                 inflating: org/apache/http/message/BasicHttpEntityEnclosingRequest.class
                 inflating: org/apache/http/UnsupportedHttpVersionException.class
                 inflating: org/apache/http/entity/BufferedHttpEntity.class
                 inflating: org/apache/http/annotation/ThreadSafe.class
                 inflating: org/apache/http/impl/io/AbstractMessageWriter.class
                 inflating: org/apache/http/impl/NoConnectionReuseStrategy.class
                 inflating: org/apache/http/protocol/HttpRequestExecutor.class
                 inflating: org/apache/http/message/BufferedHeader.class
                 inflating: org/apache/http/HttpServerConnection.class
                 inflating: org/apache/http/message/LineParser.class
                 inflating: org/apache/http/protocol/ExecutionContext.class
                 inflating: org/apache/http/impl/DefaultHttpClientConnection.class
                 inflating: org/apache/http/protocol/BasicHttpProcessor.class
                 inflating: org/apache/http/message/BasicHttpResponse.class
                 inflating: org/apache/http/message/BasicRequestLine.class
                 inflating: org/apache/http/RequestLine.class
                 inflating: org/apache/http/util/LangUtils.class
                 inflating: org/apache/http/util/EntityUtils.class
                 inflating: org/apache/http/impl/io/HttpRequestWriter.class
                 inflating: org/apache/http/protocol/RequestTargetHost.class
                  creating: org/apache/http/concurrent/
                 inflating: org/apache/http/concurrent/Cancellable.class
                 inflating: org/apache/http/protocol/HttpDateGenerator.class
                 inflating: org/apache/http/HttpInetConnection.class
                 inflating: org/apache/http/impl/io/AbstractSessionOutputBuffer.class
                 inflating: org/apache/http/entity/ContentProducer.class
                 inflating: org/apache/http/message/HeaderValueParser.class
                 inflating: org/apache/http/impl/AbstractHttpServerConnection.class
                 inflating: org/apache/http/entity/AbstractHttpEntity.class
                 inflating: org/apache/http/HttpResponse.class
                 inflating: org/apache/http/pool/ConnPool.class
                 inflating: org/apache/http/message/BasicHeaderValueParser.class
                 inflating: org/apache/http/protocol/ResponseServer.class
                 inflating: org/apache/http/HttpHost.class
                 inflating: org/apache/http/protocol/RequestExpectContinue.class
                 inflating: org/apache/http/params/DefaultedHttpParams.class
                 inflating: org/apache/http/message/BasicTokenIterator.class
                 inflating: org/apache/http/protocol/HttpService.class
                 inflating: org/apache/http/util/VersionInfo.class
                 inflating: org/apache/http/params/BasicHttpParams.class
                 inflating: org/apache/http/NameValuePair.class
                 inflating: org/apache/http/HttpConnectionMetrics.class
                 inflating: org/apache/http/ConnectionClosedException.class
                 inflating: org/apache/http/protocol/RequestUserAgent.class
                 inflating: org/apache/http/entity/EntityTemplate.class
                 inflating: org/apache/http/protocol/BasicHttpContext.class
                 inflating: org/apache/http/TruncatedChunkException.class
                 inflating: org/apache/http/params/SyncBasicHttpParams.class
                 inflating: org/apache/http/HttpRequestFactory.class
                 inflating: org/apache/http/HttpVersion.class
                 inflating: org/apache/http/impl/io/DefaultHttpRequestParser.class
                 inflating: org/apache/http/message/ParserCursor.class
                 inflating: org/apache/http/entity/HttpEntityWrapper.class
                 inflating: org/apache/http/ReasonPhraseCatalog.class
                 inflating: org/apache/http/HttpRequestInterceptor.class
                 inflating: org/apache/http/message/BasicNameValuePair.class
                 inflating: org/apache/http/annotation/NotThreadSafe.class
                 inflating: org/apache/http/HttpClientConnection.class
                 inflating: org/apache/http/impl/HttpConnectionMetricsImpl.class
                 inflating: org/apache/http/annotation/GuardedBy.class
                 inflating: org/apache/http/HttpHeaders.class
                 inflating: org/apache/http/impl/io/ContentLengthInputStream.class
                 inflating: org/apache/http/impl/io/HttpResponseParser.class
                 inflating: org/apache/http/util/ExceptionUtils.class
                 inflating: org/apache/http/entity/StringEntity.class
                 inflating: org/apache/http/ContentTooLongException.class
                 inflating: org/apache/http/impl/EnglishReasonPhraseCatalog.class
                 inflating: org/apache/http/protocol/DefaultedHttpContext.class
                 inflating: org/apache/http/impl/DefaultHttpServerConnection.class
                 inflating: org/apache/http/impl/io/HttpRequestParser.class
                 inflating: org/apache/http/pool/ConnFactory.class
                 inflating: org/apache/http/HttpEntityEnclosingRequest.class
                 inflating: org/apache/http/params/HttpParams.class
                 inflating: org/apache/http/impl/io/HttpResponseWriter.class
                 inflating: org/apache/http/params/HttpProtocolParams.class
                 inflating: org/apache/http/protocol/HttpProcessor.class
                 inflating: org/apache/http/protocol/HttpResponseInterceptorList.class
                 inflating: org/apache/http/message/BasicHeaderElementIterator.class
                 inflating: org/apache/http/util/CharArrayBuffer.class
                 inflating: org/apache/http/impl/entity/StrictContentLengthStrategy.class
                 inflating: org/apache/http/io/SessionOutputBuffer.class
                 inflating: org/apache/http/impl/DefaultHttpRequestFactory.class
                 inflating: org/apache/http/params/CoreConnectionPNames.class
                 inflating: org/apache/http/params/HttpAbstractParamBean.class
                 inflating: org/apache/http/message/BasicHeaderValueFormatter.class
                 inflating: org/apache/http/HttpEntity.class
                 inflating: org/apache/http/pool/AbstractConnPool$1.class
                 inflating: org/apache/http/HeaderElementIterator.class
                 inflating: org/apache/http/io/SessionInputBuffer.class
                 inflating: org/apache/http/HttpStatus.class
                 inflating: org/apache/http/impl/io/ChunkedInputStream.class
                 inflating: org/apache/http/io/HttpTransportMetrics.class
                 inflating: org/apache/http/impl/io/IdentityInputStream.class
                 inflating: org/apache/http/ParseException.class
                 inflating: org/apache/http/protocol/ImmutableHttpProcessor.class
                 inflating: org/apache/http/message/LineFormatter.class
                 inflating: org/apache/http/impl/DefaultConnectionReuseStrategy.class
                 inflating: org/apache/http/NoHttpResponseException.class
                 inflating: org/apache/http/protocol/HttpExpectationVerifier.class
                 inflating: org/apache/http/pool/ConnPoolControl.class
                 inflating: org/apache/http/version.properties
                 inflating: org/apache/http/impl/io/DefaultHttpResponseParser.class
                 inflating: org/apache/http/concurrent/BasicFuture.class
                 inflating: org/apache/http/ProtocolException.class
                 inflating: org/apache/http/concurrent/FutureCallback.class
                 inflating: org/apache/http/entity/ContentLengthStrategy.class
                 inflating: org/apache/http/Consts.class
                  creating: org/apache/http/impl/cookie/
                 inflating: org/apache/http/impl/cookie/RFC2109VersionHandler.class
                  creating: org/apache/http/impl/conn/
                  creating: org/apache/http/impl/conn/tsccm/
                 inflating: org/apache/http/impl/conn/tsccm/BasicPoolEntryRef.class
                  creating: org/apache/http/impl/client/
                 inflating: org/apache/http/impl/client/TunnelRefusedException.class
                  creating: org/apache/http/client/
                 inflating: org/apache/http/client/CredentialsProvider.class
                 inflating: org/apache/http/impl/client/AbstractHttpClient.class
                 inflating: org/apache/http/impl/cookie/NetscapeDraftSpec.class
                  creating: org/apache/http/cookie/
                 inflating: org/apache/http/cookie/Cookie.class
                 inflating: org/apache/http/cookie/CookieOrigin.class
                  creating: org/apache/http/conn/
                  creating: org/apache/http/conn/routing/
                 inflating: org/apache/http/conn/routing/RouteInfo$TunnelType.class
                 inflating: org/apache/http/impl/cookie/PublicSuffixListParser.class
                  creating: org/apache/http/conn/scheme/
                 inflating: org/apache/http/conn/scheme/SchemeLayeredSocketFactoryAdaptor.class
                  creating: org/apache/http/impl/auth/
                 inflating: org/apache/http/impl/auth/NTLMEngine.class
                 inflating: org/apache/http/client/AuthenticationStrategy.class
                 inflating: org/apache/http/impl/client/ContentEncodingHttpClient.class
                 inflating: org/apache/http/impl/client/AuthenticationStrategyImpl.class
                 inflating: org/apache/http/conn/scheme/LayeredSchemeSocketFactory.class
                 inflating: org/apache/http/impl/auth/NTLMEngineImpl.class
                 inflating: org/apache/http/impl/auth/NTLMEngineImpl$Type1Message.class
                 inflating: org/apache/http/conn/scheme/PlainSocketFactory.class
                 inflating: org/apache/http/impl/cookie/BestMatchSpec.class
                 inflating: org/apache/http/impl/cookie/BrowserCompatVersionAttributeHandler.class
                 inflating: org/apache/http/conn/scheme/SocketFactoryAdaptor.class
                  creating: org/apache/http/client/utils/
                 inflating: org/apache/http/client/utils/Idn.class
                 inflating: org/apache/http/impl/conn/tsccm/RouteSpecificPool$1.class
                 inflating: org/apache/http/impl/conn/IdleConnectionHandler$TimeValues.class
                  creating: org/apache/http/cookie/params/
                 inflating: org/apache/http/cookie/params/CookieSpecParamBean.class
                 inflating: org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter.class
                 inflating: org/apache/http/impl/client/ProxyClient$ProxyConnection.class
                  creating: org/apache/http/client/protocol/
                 inflating: org/apache/http/client/protocol/RequestAddCookies.class
                 inflating: org/apache/http/impl/cookie/NetscapeDraftSpecFactory.class
                 inflating: org/apache/http/impl/client/ClientParamsStack.class
                 inflating: org/apache/http/client/NonRepeatableRequestException.class
                 inflating: org/apache/http/impl/cookie/RFC2965CommentUrlAttributeHandler.class
                  creating: org/apache/http/auth/
                 inflating: org/apache/http/auth/AuthProtocolState.class
                 inflating: org/apache/http/impl/cookie/RFC2109Spec.class
                 inflating: org/apache/http/impl/conn/DefaultHttpResponseParser.class
                 inflating: org/apache/http/impl/cookie/NetscapeDraftHeaderParser.class
                 inflating: org/apache/http/impl/auth/HttpEntityDigester.class
                 inflating: org/apache/http/client/HttpClient.class
                 inflating: org/apache/http/impl/client/TargetAuthenticationStrategy.class
                  creating: org/apache/http/client/methods/
                 inflating: org/apache/http/client/methods/HttpPost.class
                  creating: org/apache/http/conn/params/
                 inflating: org/apache/http/conn/params/ConnManagerPNames.class
                 inflating: org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager.class
                  creating: org/apache/http/conn/ssl/
                 inflating: org/apache/http/conn/ssl/AllowAllHostnameVerifier.class
                 inflating: org/apache/http/client/protocol/ClientContext.class
                 inflating: org/apache/http/client/RedirectHandler.class
                 inflating: org/apache/http/impl/client/AIMDBackoffManager.class
                 inflating: org/apache/http/conn/ssl/StrictHostnameVerifier.class
                 inflating: org/apache/http/conn/OperatedClientConnection.class
                 inflating: org/apache/http/conn/params/ConnConnectionParamBean.class
                 inflating: org/apache/http/impl/cookie/BasicSecureHandler.class
                 inflating: org/apache/http/impl/auth/BasicScheme.class
                 inflating: org/apache/http/impl/auth/NTLMEngineImpl$MD4.class
                 inflating: org/apache/http/auth/ContextAwareAuthScheme.class
                 inflating: org/apache/http/client/methods/HttpEntityEnclosingRequestBase.class
                 inflating: org/apache/http/impl/conn/tsccm/PoolEntryRequest.class
                 inflating: org/apache/http/conn/ssl/TrustStrategy.class
                  creating: org/apache/http/auth/params/
                 inflating: org/apache/http/auth/params/AuthParamBean.class
                 inflating: org/apache/http/impl/client/DefaultRedirectHandler.class
                 inflating: org/apache/http/cookie/SM.class
                 inflating: org/apache/http/conn/ClientConnectionManager.class
                 inflating: org/apache/http/conn/params/ConnRoutePNames.class
                 inflating: org/apache/http/client/methods/HttpTrace.class
                 inflating: org/apache/http/auth/ChallengeState.class
                 inflating: org/apache/http/client/version.properties
                 inflating: org/apache/http/conn/params/ConnManagerParams.class
                 inflating: org/apache/http/impl/cookie/DateUtils$DateFormatHolder.class
                 inflating: org/apache/http/impl/conn/DefaultClientConnectionOperator.class
                 inflating: org/apache/http/impl/cookie/RFC2109SpecFactory.class
                 inflating: org/apache/http/impl/conn/DefaultResponseParser.class
                 inflating: org/apache/http/cookie/CookieAttributeHandler.class
                 inflating: org/apache/http/impl/cookie/BestMatchSpecFactory.class
                 inflating: org/apache/http/conn/MultihomePlainSocketFactory.class
                 inflating: org/apache/http/client/protocol/RequestTargetAuthentication.class
                 inflating: org/apache/http/impl/cookie/BasicClientCookie.class
                 inflating: org/apache/http/conn/scheme/SchemeSocketFactoryAdaptor.class
                 inflating: org/apache/http/impl/auth/KerberosScheme.class
                 inflating: org/apache/http/conn/BasicManagedEntity.class
                 inflating: org/apache/http/impl/conn/HttpPoolEntry.class
                 inflating: org/apache/http/cookie/SetCookie.class
                 inflating: org/apache/http/conn/scheme/Scheme.class
                 inflating: org/apache/http/client/utils/URLEncodedUtils.class
                 inflating: org/apache/http/impl/conn/LoggingSessionOutputBuffer.class
                 inflating: org/apache/http/impl/client/DefaultUserTokenHandler.class
                 inflating: org/apache/http/conn/ConnectTimeoutException.class
                 inflating: org/apache/http/conn/ConnectionKeepAliveStrategy.class
                 inflating: org/apache/http/auth/AuthenticationException.class
                 inflating: org/apache/http/impl/conn/tsccm/AbstractConnPool.class
                 inflating: org/apache/http/impl/client/EntityEnclosingRequestWrapper$EntityWrapper.class
                 inflating: org/apache/http/impl/conn/DefaultClientConnection.class
                 inflating: org/apache/http/impl/client/DefaultBackoffStrategy.class
                 inflating: org/apache/http/impl/cookie/BasicPathHandler.class
                 inflating: org/apache/http/cookie/CookieIdentityComparator.class
                 inflating: org/apache/http/impl/cookie/RFC2965Spec.class
                 inflating: org/apache/http/client/methods/HttpHead.class
                 inflating: org/apache/http/client/utils/HttpClientUtils.class
                 inflating: org/apache/http/client/methods/HttpPut.class
                 inflating: org/apache/http/client/protocol/ResponseProcessCookies.class
                 inflating: org/apache/http/impl/cookie/AbstractCookieAttributeHandler.class
                 inflating: org/apache/http/impl/auth/RFC2617Scheme.class
                 inflating: org/apache/http/client/utils/Rfc3492Idn.class
                 inflating: org/apache/http/impl/client/BasicResponseHandler.class
                 inflating: org/apache/http/cookie/params/CookieSpecPNames.class
                 inflating: org/apache/http/conn/ManagedClientConnection.class
                 inflating: org/apache/http/impl/conn/tsccm/WaitingThreadAborter.class
                 inflating: org/apache/http/conn/HttpHostConnectException.class
                 inflating: org/apache/http/conn/params/ConnRouteParamBean.class
                 inflating: org/apache/http/impl/auth/KerberosSchemeFactory.class
                 inflating: org/apache/http/impl/client/DefaultRedirectStrategy.class
                 inflating: org/apache/http/auth/NTCredentials.class
                 inflating: org/apache/http/client/RedirectException.class
                 inflating: org/apache/http/client/methods/HttpGet.class
                 inflating: org/apache/http/impl/conn/SingleClientConnManager$ConnAdapter.class
                 inflating: org/apache/http/cookie/CookieSpec.class
                 inflating: org/apache/http/auth/MalformedChallengeException.class
                 inflating: org/apache/http/impl/client/RedirectLocations.class
                 inflating: org/apache/http/impl/client/ProxyAuthenticationStrategy.class
                 inflating: org/apache/http/conn/scheme/SocketFactory.class
                 inflating: org/apache/http/impl/auth/BasicSchemeFactory.class
                 inflating: org/apache/http/conn/routing/RouteInfo.class
                 inflating: org/apache/http/impl/auth/SpnegoTokenGenerator.class
                 inflating: org/apache/http/impl/conn/ProxySelectorRoutePlanner$1.class
                 inflating: org/apache/http/conn/scheme/SchemeSocketFactory.class
                 inflating: org/apache/http/impl/auth/NTLMEngineException.class
                 inflating: org/apache/http/impl/client/SystemClock.class
                 inflating: org/apache/http/client/AuthCache.class
                 inflating: org/apache/http/impl/client/DefaultHttpClient.class
                 inflating: org/apache/http/conn/ConnectionReleaseTrigger.class
                 inflating: org/apache/http/client/methods/AbortableHttpRequest.class
                  creating: org/apache/http/client/entity/
                 inflating: org/apache/http/client/entity/DecompressingEntity.class
                 inflating: org/apache/http/impl/conn/Wire.class
                 inflating: org/apache/http/auth/AuthSchemeFactory.class
                 inflating: org/apache/http/cookie/CookieSpecRegistry.class
                 inflating: org/apache/http/conn/BasicEofSensorWatcher.class
                 inflating: org/apache/http/impl/client/AuthenticationStrategyAdaptor.class
                 inflating: org/apache/http/cookie/CookieRestrictionViolationException.class
                 inflating: org/apache/http/impl/cookie/BasicClientCookie2.class
                 inflating: org/apache/http/impl/client/RequestWrapper.class
                 inflating: org/apache/http/conn/routing/RouteTracker.class
                 inflating: org/apache/http/conn/HttpRoutedConnection.class
                 inflating: org/apache/http/auth/Credentials.class
                 inflating: org/apache/http/client/protocol/RequestAuthenticationBase$1.class
                 inflating: org/apache/http/conn/params/ConnConnectionPNames.class
                 inflating: org/apache/http/impl/cookie/DateUtils.class
                 inflating: org/apache/http/client/RequestDirector.class
                 inflating: org/apache/http/client/protocol/RequestProxyAuthentication.class
                 inflating: org/apache/http/client/BackoffManager.class
                 inflating: org/apache/http/impl/conn/HttpConnPool$InternalConnFactory.class
                 inflating: org/apache/http/auth/params/AuthPNames.class
                 inflating: org/apache/http/impl/client/DefaultRedirectStrategyAdaptor.class
                 inflating: org/apache/http/client/CookieStore.class
                 inflating: org/apache/http/impl/auth/NTLMSchemeFactory.class
                 inflating: org/apache/http/client/utils/JdkIdn.class
                 inflating: org/apache/http/impl/conn/LoggingSessionInputBuffer.class
                  creating: org/apache/http/client/params/
                 inflating: org/apache/http/client/params/CookiePolicy.class
                 inflating: org/apache/http/impl/cookie/BasicExpiresHandler.class
                 inflating: org/apache/http/impl/client/DecompressingHttpClient.class
                 inflating: org/apache/http/conn/params/ConnPerRouteBean.class
                 inflating: org/apache/http/auth/BasicUserPrincipal.class
                 inflating: org/apache/http/auth/AUTH.class
                 inflating: org/apache/http/client/params/HttpClientParams.class
                 inflating: org/apache/http/impl/auth/NTLMEngineImpl$Type2Message.class
                 inflating: org/apache/http/conn/ClientConnectionRequest.class
                 inflating: org/apache/http/conn/scheme/HostNameResolver.class
                 inflating: org/apache/http/client/entity/DeflateDecompressingEntity.class
                 inflating: org/apache/http/impl/conn/IdleConnectionHandler.class
                 inflating: org/apache/http/client/params/ClientParamBean.class
                 inflating: org/apache/http/impl/auth/NTLMEngineImpl$HMACMD5.class
                 inflating: org/apache/http/impl/cookie/BasicDomainHandler.class
                 inflating: org/apache/http/client/UserTokenHandler.class
                 inflating: org/apache/http/conn/ssl/SSLSocketFactory.class
                 inflating: org/apache/http/impl/client/DefaultProxyAuthenticationHandler.class
                 inflating: org/apache/http/client/ServiceUnavailableRetryStrategy.class
                 inflating: org/apache/http/client/methods/HttpUriRequest.class
                 inflating: org/apache/http/impl/conn/SystemDefaultDnsResolver.class
                  creating: META-INF/maven/org.apache.httpcomponents/httpclient/
                 inflating: META-INF/maven/org.apache.httpcomponents/httpclient/pom.properties
                 inflating: org/apache/http/impl/conn/SchemeRegistryFactory.class
                 inflating: org/apache/http/impl/client/ProxyClient.class
                 inflating: org/apache/http/auth/InvalidCredentialsException.class
                 inflating: org/apache/http/client/AuthenticationHandler.class
                 inflating: org/apache/http/conn/ssl/BrowserCompatHostnameVerifier.class
                 inflating: org/apache/http/client/protocol/RequestAuthenticationBase.class
                 inflating: org/apache/http/impl/auth/GGSSchemeBase$State.class
                 inflating: org/apache/http/impl/cookie/NetscapeDomainHandler.class
                 inflating: org/apache/http/conn/ssl/X509HostnameVerifier.class
                 inflating: org/apache/http/impl/client/StandardHttpRequestRetryHandler.class
                 inflating: org/apache/http/conn/ssl/AbstractVerifier.class
                 inflating: org/apache/http/cookie/CookiePathComparator.class
                 inflating: org/apache/http/client/protocol/ResponseContentEncoding.class
                 inflating: org/apache/http/impl/client/HttpAuthenticator$1.class
                 inflating: org/apache/http/client/params/AllClientPNames.class
                 inflating: org/apache/http/conn/EofSensorInputStream.class
                 inflating: org/apache/http/cookie/ClientCookie.class
                 inflating: org/apache/http/impl/client/BasicCredentialsProvider.class
                 inflating: org/apache/http/conn/ssl/TrustSelfSignedStrategy.class
                 inflating: org/apache/http/impl/conn/PoolingClientConnectionManager$1.class
                 inflating: org/apache/http/conn/routing/HttpRoute.class
                 inflating: org/apache/http/impl/auth/DigestScheme.class
                 inflating: org/apache/http/client/utils/Punycode.class
                 inflating: org/apache/http/impl/cookie/DateParseException.class
                 inflating: org/apache/http/impl/cookie/PublicSuffixFilter.class
                 inflating: org/apache/http/impl/client/LaxRedirectStrategy.class
                 inflating: org/apache/http/impl/cookie/BrowserCompatSpec.class
                 inflating: org/apache/http/client/utils/CloneUtils.class
                 inflating: org/apache/http/impl/conn/tsccm/BasicPoolEntry.class
                 inflating: org/apache/http/client/protocol/RequestAcceptEncoding.class
                 inflating: org/apache/http/conn/params/ConnManagerParamBean.class
                 inflating: org/apache/http/impl/conn/ManagedClientConnectionImpl.class
                 inflating: org/apache/http/impl/client/DefaultTargetAuthenticationHandler.class
                 inflating: org/apache/http/impl/client/DefaultConnectionKeepAliveStrategy.class
                 inflating: org/apache/http/impl/client/EntityEnclosingRequestWrapper.class
                 inflating: org/apache/http/conn/params/ConnRouteParams.class
                 inflating: org/apache/http/impl/cookie/BrowserCompatSpecFactory.class
                 inflating: org/apache/http/impl/client/DefaultRequestDirector.class
                 inflating: org/apache/http/conn/routing/HttpRoutePlanner.class
                 inflating: org/apache/http/client/methods/HttpDelete.class
                 inflating: org/apache/http/impl/conn/tsccm/RouteSpecificPool.class
                 inflating: org/apache/http/conn/HttpInetSocketAddress.class
                 inflating: org/apache/http/client/ResponseHandler.class
                 inflating: org/apache/http/client/HttpResponseException.class
                 inflating: org/apache/http/auth/AuthState.class
                 inflating: org/apache/http/client/HttpRequestRetryHandler.class
                 inflating: org/apache/http/impl/client/Clock.class
                 inflating: org/apache/http/cookie/CookieSpecFactory.class
                 inflating: org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager$1.class
                 inflating: org/apache/http/impl/auth/SPNegoScheme.class
                 inflating: org/apache/http/conn/ConnectionPoolTimeoutException.class
                 inflating: org/apache/http/auth/AuthScope.class
                 inflating: org/apache/http/client/protocol/ClientContextConfigurer.class
                 inflating: org/apache/http/impl/conn/HttpConnPool.class
                 inflating: org/apache/http/auth/AuthScheme.class
                 inflating: org/apache/http/impl/cookie/AbstractCookieSpec.class
                 inflating: org/apache/http/conn/ClientConnectionManagerFactory.class
                 inflating: org/apache/http/impl/conn/AbstractClientConnAdapter.class
                 inflating: org/apache/http/cookie/MalformedCookieException.class
                 inflating: org/apache/http/impl/conn/SingleClientConnManager.class
                 inflating: org/apache/http/client/entity/DeflateDecompressingEntity$DeflateStream.class
                 inflating: org/apache/http/impl/conn/tsccm/ConnPoolByRoute.class
                 inflating: org/apache/http/conn/ssl/TrustManagerDecorator.class
                 inflating: org/apache/http/impl/cookie/RFC2965PortAttributeHandler.class
                 inflating: org/apache/http/client/ClientProtocolException.class
                 inflating: org/apache/http/impl/client/AbstractAuthenticationHandler.class
                 inflating: org/apache/http/impl/auth/NTLMScheme.class
                 inflating: org/apache/http/client/protocol/RequestDefaultHeaders.class
                 inflating: org/apache/http/impl/cookie/RFC2965DiscardAttributeHandler.class
                 inflating: org/apache/http/impl/conn/DefaultHttpRoutePlanner.class
                 inflating: org/apache/http/client/entity/GzipDecompressingEntity.class
                 inflating: org/apache/http/impl/client/BasicCookieStore.class
                 inflating: org/apache/http/client/methods/HttpRequestBase.class
                 inflating: org/apache/http/impl/cookie/IgnoreSpec.class
                 inflating: org/apache/http/client/utils/URIUtils.class
                 inflating: org/apache/http/conn/routing/HttpRouteDirector.class
                 inflating: org/apache/http/auth/UsernamePasswordCredentials.class
                 inflating: org/apache/http/client/entity/UrlEncodedFormEntity.class
                 inflating: org/apache/http/conn/routing/BasicRouteDirector.class
                 inflating: org/apache/http/conn/scheme/SchemeLayeredSocketFactory.class
                 inflating: org/apache/http/impl/auth/NegotiateSchemeFactory.class
                 inflating: org/apache/http/impl/auth/NTLMEngineImpl$CipherGen.class
                 inflating: org/apache/http/impl/client/BasicAuthCache.class
                 inflating: org/apache/http/client/ConnectionBackoffStrategy.class
                 inflating: org/apache/http/impl/cookie/BasicCommentHandler.class
                 inflating: org/apache/http/impl/auth/UnsupportedDigestAlgorithmException.class
                 inflating: org/apache/http/impl/cookie/BasicMaxAgeHandler.class
                 inflating: org/apache/http/client/protocol/RequestAuthCache.class
                 inflating: org/apache/http/client/methods/HttpOptions.class
                 inflating: org/apache/http/auth/NTUserPrincipal.class
                 inflating: org/apache/http/impl/conn/tsccm/WaitingThread.class
                 inflating: org/apache/http/impl/cookie/RFC2965DomainAttributeHandler.class
                 inflating: org/apache/http/impl/conn/AbstractPooledConnAdapter.class
                 inflating: org/apache/http/impl/auth/NTLMScheme$State.class
                 inflating: org/apache/http/conn/routing/RouteInfo$LayerType.class
                 inflating: org/apache/http/impl/auth/AuthSchemeBase.class
                 inflating: org/apache/http/impl/cookie/RFC2965VersionAttributeHandler.class
                 inflating: org/apache/http/impl/conn/ProxySelectorRoutePlanner.class
                 inflating: org/apache/http/conn/ssl/SSLInitializationException.class
                 inflating: org/apache/http/impl/auth/GGSSchemeBase.class
                 inflating: org/apache/http/impl/client/RoutedRequest.class
                 inflating: org/apache/http/impl/auth/NegotiateScheme.class
                 inflating: org/apache/http/impl/conn/PoolingClientConnectionManager.class
                 inflating: org/apache/http/impl/cookie/IgnoreSpecFactory.class
                 inflating: org/apache/http/conn/scheme/LayeredSocketFactory.class
                 inflating: org/apache/http/impl/auth/NTLMEngineImpl$NTLMMessage.class
                 inflating: org/apache/http/client/methods/HttpPatch.class
                 inflating: org/apache/http/impl/auth/SPNegoSchemeFactory.class
                 inflating: org/apache/http/conn/EofSensorWatcher.class
                 inflating: org/apache/http/conn/params/ConnPerRoute.class
                 inflating: org/apache/http/impl/auth/DigestSchemeFactory.class
                 inflating: org/apache/http/client/RedirectStrategy.class
                 inflating: org/apache/http/impl/cookie/RFC2109DomainHandler.class
                 inflating: org/apache/http/impl/conn/BasicClientConnectionManager$1.class
                 inflating: META-INF/maven/org.apache.httpcomponents/httpclient/pom.xml
                 inflating: org/apache/http/impl/client/DefaultHttpRequestRetryHandler.class
                 inflating: org/apache/http/auth/AuthOption.class
                 inflating: org/apache/http/impl/client/NullBackoffStrategy.class
                 inflating: org/apache/http/impl/client/HttpAuthenticator.class
                 inflating: org/apache/http/client/protocol/RequestClientConnControl.class
                 inflating: org/apache/http/client/utils/URIBuilder.class
                 inflating: org/apache/http/impl/conn/ConnectionShutdownException.class
                 inflating: org/apache/http/conn/scheme/LayeredSocketFactoryAdaptor.class
                 inflating: org/apache/http/impl/conn/tsccm/ConnPoolByRoute$1.class
                  creating: org/apache/http/conn/util/
                 inflating: org/apache/http/conn/util/InetAddressUtils.class
                 inflating: org/apache/http/impl/conn/SingleClientConnManager$PoolEntry.class
                 inflating: org/apache/http/impl/client/SystemDefaultHttpClient.class
                 inflating: org/apache/http/cookie/SetCookie2.class
                 inflating: org/apache/http/client/protocol/ResponseAuthCache$1.class
                 inflating: org/apache/http/client/params/AuthPolicy.class
                 inflating: org/apache/http/conn/params/ConnManagerParams$1.class
                 inflating: org/apache/http/impl/auth/NTLMEngineImpl$Type3Message.class
                 inflating: org/apache/http/impl/client/DefaultServiceUnavailableRetryStrategy.class
                 inflating: org/apache/http/conn/DnsResolver.class
                 inflating: org/apache/http/conn/scheme/SchemeRegistry.class
                 inflating: org/apache/http/auth/AuthSchemeRegistry.class
                 inflating: org/apache/http/impl/cookie/CookieSpecBase.class
                 inflating: org/apache/http/impl/cookie/DateUtils$DateFormatHolder$1.class
                 inflating: org/apache/http/impl/conn/BasicClientConnectionManager.class
                 inflating: org/apache/http/client/params/ClientPNames.class
                 inflating: org/apache/http/conn/scheme/SchemeLayeredSocketFactoryAdaptor2.class
                 inflating: org/apache/http/impl/conn/InMemoryDnsResolver.class
                 inflating: org/apache/http/client/CircularRedirectException.class
                 inflating: org/apache/http/conn/ClientConnectionOperator.class
                 inflating: org/apache/http/impl/client/AutoRetryHttpClient.class
                 inflating: org/apache/http/impl/cookie/RFC2965SpecFactory.class
                 inflating: org/apache/http/auth/params/AuthParams.class
                 inflating: org/apache/http/impl/conn/SingleClientConnManager$1.class
                 inflating: org/apache/http/impl/auth/GGSSchemeBase$1.class
                 inflating: org/apache/http/impl/conn/AbstractPoolEntry.class
                 inflating: org/apache/http/client/protocol/ResponseAuthCache.class
                  creating: org/apache/commons/logging/
                  creating: org/apache/commons/logging/impl/
                 inflating: org/apache/commons/logging/impl/SimpleLog.class
                 inflating: org/apache/commons/logging/impl/LogFactoryImpl$3.class
                 inflating: org/apache/commons/logging/impl/Jdk13LumberjackLogger.class
                 inflating: org/apache/commons/logging/impl/ServletContextCleaner.class
                 inflating: org/apache/commons/logging/impl/WeakHashtable$1.class
                 inflating: org/apache/commons/logging/LogSource.class
                 inflating: org/apache/commons/logging/LogConfigurationException.class
                 inflating: org/apache/commons/logging/LogFactory$1.class
                 inflating: org/apache/commons/logging/Log.class
                 inflating: org/apache/commons/logging/LogFactory$5.class
                 inflating: org/apache/commons/logging/impl/SimpleLog$1.class
                 inflating: org/apache/commons/logging/impl/LogFactoryImpl$2.class
                 inflating: org/apache/commons/logging/impl/NoOpLog.class
                 inflating: org/apache/commons/logging/impl/Jdk14Logger.class
                 inflating: META-INF/NOTICE
                 inflating: org/apache/commons/logging/impl/WeakHashtable$WeakKey.class
                 inflating: org/apache/commons/logging/impl/LogKitLogger.class
                 inflating: org/apache/commons/logging/impl/AvalonLogger.class
                 inflating: org/apache/commons/logging/impl/LogFactoryImpl.class
                 inflating: org/apache/commons/logging/LogFactory.class
                  creating: META-INF/maven/commons-logging/
                  creating: META-INF/maven/commons-logging/commons-logging/
                 inflating: META-INF/maven/commons-logging/commons-logging/pom.xml
                 inflating: org/apache/commons/logging/impl/LogFactoryImpl$1.class
                 inflating: META-INF/maven/commons-logging/commons-logging/pom.properties
                 inflating: META-INF/LICENSE
                 inflating: org/apache/commons/logging/LogFactory$6.class
                 inflating: org/apache/commons/logging/LogFactory$3.class
                 inflating: org/apache/commons/logging/impl/WeakHashtable.class
                 inflating: org/apache/commons/logging/impl/WeakHashtable$Entry.class
                 inflating: org/apache/commons/logging/impl/Log4JLogger.class
                 inflating: org/apache/commons/logging/impl/WeakHashtable$Referenced.class
                 inflating: org/apache/commons/logging/LogFactory$2.class
                 inflating: org/apache/commons/logging/LogFactory$4.class
               ✔ ~/dev/repro/leo_liang/dist [master|✔]
```
