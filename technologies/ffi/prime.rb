require 'ffi'

module Prime
  extend FFI::Library
  ffi_lib './prime.so'
  attach_function :gcd, [:long, :long], :long
end

puts (Prime.gcd 120,30)
