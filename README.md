# Illumio-Coding-Challenge

## Implementation
A HashMap is used to back the implementation. I concatenated the direction, protocol, and port number with a '@' symbol together as the key of HashMap, IP or IP range as the value of HashMap.

## Test
`Junit` is used to test the code. Common inputs with/out IP/Port ranges, with/out overlapping ranges, and the inclusiveness of ranges are tested.

## Benchmark
Query a million random packets from a million random policy takes `1s 992ms` after the constructor returned.

## Improvement
- Merge the overlapping IP intervals should improve the current solution, but more complicated data structures can be developed in the future, possibly utilizing Tree and HashMap.

- If the principle of locality is observed, I can add a layer of cache.
- If extra information regarding the input policy is known, I might take advantage of it during the design process.

## Team Preference
I am most interested in the Data team, and then the Platform team.
