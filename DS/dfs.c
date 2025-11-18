#include<stdio.h>
int a[20][20],visited[20],n;
void dfs(int v){
    for(int i=0;i<n;i++)
        if(a[v][i]!=0&&visited[i]==0){
            visited[i]=1;
            printf(" %d",i);
            dfs(i);
        }
}
int main(){
    int v;
    printf("Enter nnumber of vertices:");
    scanf("%d",&n);
    for(int i=0;i<n;i++)
        visited[i]=0;
    printf("Enter adjacdncy matrix(Enter 0 if there is no edge):\n");
    for(int i=0;i<n;i++)
        for(int j=0;j<n;j++)
            scanf("%d",&a[i][j]);
    printf("Enter starting vertex:");
    scanf("%d",&v);
    visited[v]=1;
    printf("Depth First Traversal:\n%d",v);
    dfs(v);
    printf("\n");
}