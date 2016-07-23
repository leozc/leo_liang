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


This is the log when building with external python 3 dependencies (asynchttp in specific)
```
13:30 $ ./pants binary examples:webserver

13:30:57 00:00 [main]
               (To run a reporting server: ./pants server)
13:30:57 00:00   [setup]
13:30:57 00:00     [parse]
               Executing tasks in goals: bootstrap -> imports -> unpack-jars -> deferred-sources -> gen -> jvm-platform-validate -> resolve -> resources -> compile -> binary
13:30:57 00:00   [bootstrap]
13:30:57 00:00     [substitute-aliased-targets]
13:30:57 00:00     [jar-dependency-management]
13:30:57 00:00     [bootstrap-jvm-tools]
13:30:57 00:00     [provide-tools-jar]
13:30:57 00:00   [imports]
13:30:57 00:00     [ivy-imports]
13:30:57 00:00   [unpack-jars]
13:30:57 00:00     [unpack-jars]
13:30:57 00:00   [deferred-sources]
13:30:57 00:00     [deferred-sources]
13:30:57 00:00   [gen]
13:30:57 00:00     [thrift]
13:30:57 00:00     [protoc]
13:30:57 00:00     [antlr]
13:30:57 00:00     [ragel]
13:30:57 00:00     [jaxb]
13:30:57 00:00     [wire]
13:30:57 00:00   [jvm-platform-validate]
13:30:57 00:00     [jvm-platform-validate]
13:30:57 00:00   [resolve]
13:30:57 00:00     [ivy]
13:30:57 00:00   [resources]
13:30:57 00:00     [prepare]
13:30:57 00:00     [services]
13:30:57 00:00   [compile]
13:30:57 00:00     [compile-jvm-prep-command]
13:30:57 00:00       [jvm_prep_command]
13:30:57 00:00     [compile-prep-command]
13:30:57 00:00     [compile]
13:30:57 00:00     [zinc]
13:30:57 00:00     [jvm-dep-check]
13:30:57 00:00   [binary]
13:30:57 00:00     [binary-jvm-prep-command]
13:30:57 00:00       [jvm_prep_command]
13:30:57 00:00     [binary-prep-command]
13:30:57 00:00     [python-binary-create]
13:30:57 00:00       [cache]
                   No cached artifacts for 1 target.
                   Invalidated 1 target.
13:30:57 00:00       [chroot]**** Failed to install aiohttp-0.22.2. stdout:
running bdist_wheel
running build
running build_py
creating build
creating build/lib.macosx-10.11-x86_64-3.5
creating build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/__init__.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/abc.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/client.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/client_reqrep.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/connector.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/errors.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/file_sender.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/hdrs.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/helpers.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/log.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/multipart.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/parsers.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/protocol.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/pytest_plugin.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/resolver.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/server.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/signals.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/streams.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/test_utils.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/web.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/web_exceptions.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/web_reqrep.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/web_urldispatcher.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/web_ws.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/websocket.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/websocket_client.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/worker.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/wsgi.py -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
running egg_info
writing aiohttp.egg-info/PKG-INFO
writing requirements to aiohttp.egg-info/requires.txt
writing dependency_links to aiohttp.egg-info/dependency_links.txt
writing top-level names to aiohttp.egg-info/top_level.txt
reading manifest file 'aiohttp.egg-info/SOURCES.txt'
reading manifest template 'MANIFEST.in'
writing manifest file 'aiohttp.egg-info/SOURCES.txt'
copying aiohttp/_websocket.c -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
copying aiohttp/_websocket.pyx -> build/lib.macosx-10.11-x86_64-3.5/aiohttp
running build_ext
building 'aiohttp._websocket' extension
creating build/temp.macosx-10.11-x86_64-3.5
creating build/temp.macosx-10.11-x86_64-3.5/aiohttp
clang -Wno-unused-result -Wsign-compare -Wunreachable-code -fno-common -dynamic -DNDEBUG -g -fwrapv -O3 -Wall -Wstrict-prototypes -I/usr/local/include -I/usr/local/opt/openssl/include -I/usr/local/opt/sqlite/include -I/usr/local/Cellar/python3/3.5.2/Frameworks/Python.framework/Versions/3.5/include/python3.5m -c aiohttp/_websocket.c -o build/temp.macosx-10.11-x86_64-3.5/aiohttp/_websocket.o
clang -bundle -undefined dynamic_lookup build/temp.macosx-10.11-x86_64-3.5/aiohttp/_websocket.o -L/usr/local/lib -L/usr/local/opt/openssl/lib -L/usr/local/opt/sqlite/lib -o build/lib.macosx-10.11-x86_64-3.5/aiohttp/_websocket.cpython-35m-darwin.so
installing to build/bdist.macosx-10.11-x86_64/wheel
running install
running install_lib
creating build/bdist.macosx-10.11-x86_64
creating build/bdist.macosx-10.11-x86_64/wheel
creating build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/__init__.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/_websocket.c -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/_websocket.cpython-35m-darwin.so -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/_websocket.pyx -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/abc.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/client.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/client_reqrep.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/connector.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/errors.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/file_sender.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/hdrs.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/helpers.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/log.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/multipart.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/parsers.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/protocol.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/pytest_plugin.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/resolver.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/server.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/signals.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/streams.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/test_utils.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/web.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/web_exceptions.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/web_reqrep.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/web_urldispatcher.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/web_ws.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/websocket.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/websocket_client.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/worker.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
copying build/lib.macosx-10.11-x86_64-3.5/aiohttp/wsgi.py -> build/bdist.macosx-10.11-x86_64/wheel/aiohttp
running install_egg_info
Copying aiohttp.egg-info to build/bdist.macosx-10.11-x86_64/wheel/aiohttp-0.22.2-py3.5.egg-info
running install_scripts

**** Failed to install aiohttp-0.22.2. stderr:
warning: no previously-included files matching '*.pyc' found anywhere in distribution
warning: no previously-included files found matching 'aiohttp/_multidict.html'
warning: no previously-included files found matching 'aiohttp/_multidict.*.so'
warning: no previously-included files found matching 'aiohttp/_multidict.pyd'
warning: no previously-included files found matching 'aiohttp/_multidict.*.pyd'
warning: no previously-included files found matching 'aiohttp/_websocket.html'
warning: no previously-included files found matching 'aiohttp/_websocket.*.so'
warning: no previously-included files found matching 'aiohttp/_websocket.pyd'
warning: no previously-included files found matching 'aiohttp/_websocket.*.pyd'
no previously-included directories found matching 'docs/_build'
aiohttp/_websocket.c:2235:28: warning: unused function '__Pyx_PyObject_AsString' [-Wunused-function]
static CYTHON_INLINE char* __Pyx_PyObject_AsString(PyObject* o) {
                           ^
aiohttp/_websocket.c:2232:32: warning: unused function '__Pyx_PyUnicode_FromString' [-Wunused-function]
static CYTHON_INLINE PyObject* __Pyx_PyUnicode_FromString(const char* c_str) {
                               ^
aiohttp/_websocket.c:2297:26: warning: unused function '__Pyx_PyObject_IsTrue' [-Wunused-function]
static CYTHON_INLINE int __Pyx_PyObject_IsTrue(PyObject* x) {
                         ^
aiohttp/_websocket.c:2347:33: warning: unused function '__Pyx_PyIndex_AsSsize_t' [-Wunused-function]
static CYTHON_INLINE Py_ssize_t __Pyx_PyIndex_AsSsize_t(PyObject* b) {
                                ^
aiohttp/_websocket.c:2409:33: warning: unused function '__Pyx_PyInt_FromSize_t' [-Wunused-function]
static CYTHON_INLINE PyObject * __Pyx_PyInt_FromSize_t(size_t ival) {
                                ^
aiohttp/_websocket.c:1684:32: warning: unused function '__Pyx_PyInt_From_long' [-Wunused-function]
static CYTHON_INLINE PyObject* __Pyx_PyInt_From_long(long value) {
                               ^
aiohttp/_websocket.c:1733:27: warning: function '__Pyx_PyInt_As_long' is not needed and will not be emitted [-Wunneeded-internal-declaration]
static CYTHON_INLINE long __Pyx_PyInt_As_long(PyObject *x) {
                          ^
aiohttp/_websocket.c:1918:26: warning: function '__Pyx_PyInt_As_int' is not needed and will not be emitted [-Wunneeded-internal-declaration]
static CYTHON_INLINE int __Pyx_PyInt_As_int(PyObject *x) {
                         ^
8 warnings generated.
Traceback (most recent call last):
  File "<stdin>", line 7, in <module>
  File "setup.py", line 108, in <module>
    setup(**args)
  File "/usr/local/Cellar/python3/3.5.2/Frameworks/Python.framework/Versions/3.5/lib/python3.5/distutils/core.py", line 148, in setup
    dist.run_commands()
  File "/usr/local/Cellar/python3/3.5.2/Frameworks/Python.framework/Versions/3.5/lib/python3.5/distutils/dist.py", line 955, in run_commands
    self.run_command(cmd)
  File "/usr/local/Cellar/python3/3.5.2/Frameworks/Python.framework/Versions/3.5/lib/python3.5/distutils/dist.py", line 974, in run_command
    cmd_obj.run()
  File "/Users/leo/dev/tmp/leo_liang/.pants.d/python-setup/interpreters/CPython-3.5.2/wheel-0.24.0-py3.5.egg/wheel/bdist_wheel.py", line 213, in run
  File "/Users/leo/dev/tmp/leo_liang/.pants.d/python-setup/interpreters/CPython-3.5.2/wheel-0.24.0-py3.5.egg/wheel/bdist_wheel.py", line 161, in get_archive_basename
  File "/Users/leo/dev/tmp/leo_liang/.pants.d/python-setup/interpreters/CPython-3.5.2/wheel-0.24.0-py3.5.egg/wheel/bdist_wheel.py", line 155, in get_tag
AssertionError


13:30:58 00:01   [complete]
               FAILURE
Exception caught: (<class 'pex.resolver.Untranslateable'>)

Exception message: Package SourcePackage(u'file:///Users/leo/dev/tmp/leo_liang/.pants.d/python-setup/resolved_requirements/CPython-3.5.2/aiohttp-0.22.2.tar.gz') is not translateable by ChainedTranslator(WheelTranslator, EggTranslator, SourceTranslator)
```
