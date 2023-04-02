from ctypes import *

prime = CDLL("./prime.so")

res = prime.gcd(20,30)

print('GCD(20,30) = ', res)

