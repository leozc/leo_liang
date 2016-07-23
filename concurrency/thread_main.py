import asyncio

@asyncio.coroutine
def slow_operation(n):
    import random
    interval = random.randrange(1,5)
    yield from asyncio.sleep(interval)
    print("Slow operation {} complete for {} seconds".format(n, interval))

@asyncio.coroutine
def main():
    yield from asyncio.wait([
        slow_operation(1),
        slow_operation(2),
        slow_operation(3),
    ])

loop = asyncio.get_event_loop()
loop.run_until_complete(main())
