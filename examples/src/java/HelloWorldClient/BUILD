jvm_app(name='main',
  basename = 'hello-world-client',
  dependencies = [
    ':main-bin'
  ],
  bundles = [
    bundle(relative_to='config', fileset=globs('config/*'))
  ]
)

# The binary, the "runnable" part:

jvm_binary(name = 'main-bin',
  basename = 'hello-world-client',
  dependencies = [
    '3rdparty:slf4j-simple',
    # '3rdparty:log4j',
    'examples/src/thrift/hello_world_service:hello-world-service-java',
    '3rdparty:thrift-0.9.2',
  ],
  source = 'HelloWorldClient.java',
  main = 'HelloWorldClient.HelloWorldClient',
)