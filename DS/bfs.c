#include<stdio.h>
int a[20][20],q[20],visited[20],n,f=-1,r=-1;
void bfs(int v){
    for(int i=0;i<n;i++){
        if(a[v][i]!=0&&visited[i]==0){
            r+=1;
            q[r]=i;
            visited[i]=1;
            printf("\n%d",i);
        }
    }
    f+=1;
    if(f>=r)
    return;
    bfs(q[f]);
}
int main(){
    int v;
    printf("Enter number of vertices:");
    scanf("%d",&n);
    for(int i=0;i<n;i++)
        visited[i]=0;
    printf("Enter adjacency matrix(enter 0 if there is no edge):\n");
    for(int i=0;i<n;i++)
        for(int j=0;j<n;j++)
            scanf("%d",&a[i][j]);
    printf("Enter starting vertex:");
    scanf("%d",&v);
    f=r=0;
    q[r]=v;
    visited[v]=1;
    printf("Breadth First Traversal:\n");
    printf("%d",v);
    bfs(v);
    if(r!=n-1)
        printf("\nBFS Traversal not possible!\n");
}