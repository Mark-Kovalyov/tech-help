class PrimeJni {

  static {
     String userDir = System.getProperty("user.dir");
     System.load(userDir + "/prime.so");
  }

  native static long gcd(long a, long b);

  native static long lcm(long a, long b);

}