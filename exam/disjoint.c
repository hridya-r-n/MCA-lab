#include<stdio.h>
#define MAX 15
int parent[MAX];
int find(int x){
    if(parent[x]==x)
        return x;
    return find(parent[x]);
}
void unionSet(int a,int b){
    int i=find(a);
    int j=find(b);
    if(i==j){
        printf("Elements are already in the same set!\n");
        return;
    }
    parent[j]=i;
}
void display(int n){
    printf("Element->Root\n");
    for(int i=1;i<=n;i++){
        printf("%d -> %d\n",i,find(i));
    }
}
int main(){
    int n,n1,n2,a,b,ch;
    printf("Enter size of set 1:");
    scanf("%d",&n1);
    int A[n1];
    
    printf("Enter elements of first set:\n");
    for(int i=0;i<n1;i++){
        scanf("%d",&A[i]);
    }
    
    printf("Enter size of set 2:");
    scanf("%d",&n2);
    int B[n2];
    printf("Enter elements of second set:\n");
    for(int i=0;i<n2;i++){
        scanf("%d",&B[i]);
    }
    n=n1+n2;

    for(int i=1;i<=n;i++)
        parent[i]=i;
    for(int i=1;i<n1;i++){
        parent(A[i])=A[0];
    }
    for(int i=1;i<n2;i++){
        parent(B[i])=B[0];
    }

    do{
        printf("Disjoint set OPerations\n1.Find\n2.Union\n3.Display\n4.Exit\nEntetr your choice:");
        scanf("%d",&ch);
        switch(ch){
            case 1:printf("Enter element to find:");
            scanf("%d",&a);
            printf("Root of %d:%d\n",a,find(a));
            break;
            case 2:printf("Enter first element:");
            scanf("%d",&a);
            printf("Enter second element:");
            scanf("%d",&b);
            unionSet(a,b);
            break;
            case 3:display(n);break;
            case 4:break;
            default:printf("Invalid choice\n");
        }
    }while(ch!=4);
}