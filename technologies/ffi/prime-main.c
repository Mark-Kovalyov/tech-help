#include <stdio.h> 
#include <stdlib.h>
#include <signal.h>

#include "prime.h"

int main(int argc, char **argv) {
  printf("LCM(125,75)=%lu\n", lcm(125,75));
  return 0;
}
