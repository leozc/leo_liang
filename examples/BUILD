python_binary(name='thread_main',
  source='thread_main.py',
  compatibility='CPython>=3.5',
)

python_binary(name='python_incompatible_syntax',
  source='print_sample.py',
  compatibility=['>=2.7','<3'],
)

python_binary(name='python2',
  source='python_version.py',
  compatibility=['>=2.7'],
)

python_binary(name='python3',
  source='python_version.py',
  compatibility=['>=3.5'],
)

python_binary(name='webserver',
  dependencies=[
   '3rdparty/python3:aiohttp', # python3 dependencies
  ],
  source='webserver.py',
  compatibility=['>=3.5'],
)
