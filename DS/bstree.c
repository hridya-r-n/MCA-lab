#include<stdio.h>
#include<stdlib.h>
typedef struct node{
    int data;
    struct node *left;
    struct node *right;
}node;
node *root=NULL;
node *create(int val){
    node *ptr=(node*)malloc(sizeof(node));
    ptr->left=ptr->right=NULL;
    ptr->data=val;
    return ptr;
}
node* createT(node *root,int val){
    int ch;
    if(root==NULL){
    root=create(val);
    }else if(val>root->data)
    root->right=createT(root->right,val);
    else if(val<root->data)
    root->left=createT(root->left,val);
    else
    root=create(val);
    return root;
}
node* insert(node *root,int val){
    if(root==NULL){
        return create(val);
    }
    if(val>root->data)
        root->right=insert(root->right,val);
    else if(val<root->data)
        root->left=insert(root->left,val);
    else{
        printf("%d already exist!\n",val);
    }
    return root;
}
node* deleteNode(node *root,int val){
    if(root==NULL){
        printf("Value not found to delete\n");
        return root;
    }
    if(val<root->data)
    root->left=deleteNode(root->left,val);
    else if(val>root->data)
    root->right=deleteNode(root->right,val);
    else{
        if(root->left==NULL){
            node *temp=root->right;
            free(root);
            return temp;
        }else if(root->right==NULL){
            node *temp=root->left;
            free(root);
            return temp;
        }
        node *temp=root;
        node *ptr=root->right;
        while(ptr->left!=NULL){
            temp=ptr;
            ptr=ptr->left;
        }
        root->data=ptr->data;
        if(temp==root){
            root->right=ptr->right;
        }else{
            temp->right=ptr->left;
        }
        free(ptr);
    }
    return root;
}
void search(node *root,int val){
    
    if(root==NULL){
        printf("Value not found!\n");
        return;
    }
    if(root->data==val){
        printf("Value found!\n");
        return;
    }
    if(val<root->data)search(root->left,val);
    else if(val>root->data) search(root->right,val);
    
}
void inorder(node *root){
    if (root == NULL) {
        return;
    }
    inorder(root->left);
    printf("%d ",root->data);
    inorder(root->right);
}
void preorder(node *root){
    if(root==NULL){
        return;
    }
    printf("%d ",root->data);
    preorder(root->left);
    preorder(root->right);
}
void postorder(node *root){
    if(root==NULL){
        return;
    }
    postorder(root->left);
    postorder(root->right);
    printf("%d ",root->data);
}
int main(){
    int val,c=1,ch;
    do{
        printf("\nBinary Search Tree\n1.Create\n2.Insert\n3.Delete\n4.Search\n5.Inorder Traversal\n6.Preorder Traversal\n7.Postorder Traversal\n8.Exit\nEnter your choice:");
        scanf("%d",&ch);
        switch(ch){
            case 1:while(c){
                       printf("Enter value of the node:");
                       scanf("%d",&val);
                       root=createT(root,val);
                       printf("Do you want to add more nodes?:(1-yes,0-no):");
                       scanf("%d",&c);
                   }
                   break;
            case 2:printf("Enter the value to insert:");
                   scanf("%d",&val);
                   root=insert(root,val);
                   break;
            case 3:printf("Enter value to delete:");
                   scanf("%d",&val);
                   root=deleteNode(root,val);
                   break;
            case 4:printf("Enter value to search:");
                   scanf("%d",&val);
                   search(root,val);
                   break;
            case 5:if(root==NULL){
                       printf("Tree Empty\n");
                   }else{
                       printf("Inorder Traversal:");
                       inorder(root);
                   }
                   break;
            case 6:if(root==NULL){
                       printf("Tree Empty\n");
                   }else{
                       printf("Preorder Traversal:");
                       preorder(root);
                   }
                   break;
            case 7:if(root==NULL){
                       printf("Tree Empty\n");
                   }else{
                       printf("Postorder Traversal:");
                       postorder(root);
                   }
                   break;
            case 8:return 0;
            default:printf("Invalid Choice!\n");
        }
    }while(ch!=8);
}