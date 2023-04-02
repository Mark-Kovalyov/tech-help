class PrimeMain {

  public static void main(String[] args) {
    System.out.println("user.dir = " + System.getProperty("user.dir"));
    System.out.println("java.library.path = " + System.getProperty("java.library.path"));
    System.out.println(PrimeJni.gcd(120,30));
  }

}