A minimal example repo to try to sort out issues raised [here](https://goo.gl/w5Tbq0).


It seems it is a problem with the flag *scope='force'*, which pants-1.1.0-rc7 works fine but pants-1.0.0 fails.


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
```
