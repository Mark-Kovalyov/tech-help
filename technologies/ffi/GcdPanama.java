class GcdPanama {


  public static void main(String[] args) {

     CLinker linker = CLinker.getInstance();
     SymbolLookup lookup = CLinker.systemLookup();
     MethodHandle gcd = linker.downcallHandle(
       lookup.lookup("gcd").get(),
       MethodType.methodType(int.class),
       FunctionDescriptor.of(Clinker.C_INT)
     );
     println((int) getpid.invokeExact());
  }

}