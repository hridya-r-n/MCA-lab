package Pack;
public class Prime{
public int checkPrime(int k){
int f=0;
for(int i=2;i<=Math.sqrt(k);i++)
{
if(k%i==0){
f=1;
break;
}
}
return(f);
}
}