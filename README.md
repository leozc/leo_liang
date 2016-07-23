Test building out Python 2 and Python 3 binary within the same pants.
```
12:56 $ ./pants  binary concurrency::

12:56:49 00:00 [main]
               (To run a reporting server: ./pants server)
12:56:49 00:00   [setup]
12:56:49 00:00     [parse]
               Executing tasks in goals: bootstrap -> imports -> unpack-jars -> deferred-sources -> gen -> jvm-platform-validate -> resolve -> compile -> resources -> binary
12:56:49 00:00   [bootstrap]
12:56:49 00:00     [substitute-aliased-targets]
12:56:49 00:00     [jar-dependency-management]
12:56:49 00:00     [bootstrap-jvm-tools]
12:56:49 00:00     [provide-tools-jar]
12:56:49 00:00   [imports]
12:56:49 00:00     [ivy-imports]
12:56:49 00:00   [unpack-jars]
12:56:49 00:00     [unpack-jars]
12:56:49 00:00   [deferred-sources]
12:56:49 00:00     [deferred-sources]
12:56:49 00:00   [gen]
12:56:49 00:00     [thrift]
12:56:49 00:00     [protoc]
12:56:49 00:00     [antlr]
12:56:49 00:00     [ragel]
12:56:49 00:00     [jaxb]
12:56:49 00:00     [wire]
12:56:49 00:00   [jvm-platform-validate]
12:56:49 00:00     [jvm-platform-validate]
12:56:49 00:00   [resolve]
12:56:49 00:00     [ivy]
12:56:49 00:00   [compile]
12:56:49 00:00     [compile-jvm-prep-command]
12:56:49 00:00       [jvm_prep_command]
12:56:49 00:00     [compile-prep-command]
12:56:49 00:00     [compile]
12:56:49 00:00     [zinc]
12:56:49 00:00     [jvm-dep-check]
12:56:49 00:00   [resources]
12:56:49 00:00     [prepare]
12:56:49 00:00     [services]
12:56:49 00:00   [binary]
12:56:49 00:00     [binary-jvm-prep-command]
12:56:49 00:00       [jvm_prep_command]
12:56:49 00:00     [binary-prep-command]
12:56:49 00:00     [python-binary-create]
12:56:49 00:00       [cache]
                   No cached artifacts for 2 targets.
                   Invalidated 2 targets.
12:56:49 00:00       [chroot]
                   created pex copy dist/python2.pex
12:56:49 00:00       [chroot]
                   created pex copy dist/python3.pex
                   created pex copy dist/python_incompatible_syntax.pex
                   created pex copy dist/thread_main.pex
12:56:50 00:01     [jvm]
12:56:50 00:01     [dup]
               Waiting for background workers to finish.
12:56:50 00:01   [complete]
               SUCCESS
12:56 $ dist/python2.pex
python2
12:56 $ dist/python3.pex
python3
12:57 $ dist/python_incompatible_syntax.pex
I will break in python 3
```
