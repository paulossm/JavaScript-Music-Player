/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import java.io.IOException;
import exceptions.LoginException;
import java.util.logging.Level;
import java.util.logging.Logger;
import scripting.Connection;

/**
 * Class UsersBST
 * 
 * Implements a Binary Search Tree
 * for the application users
 * and enable find, insert and remove
 * 
 * @author Paulo
 */
public class UsersBST {
    /**
     * Node root:
     *  tree's root
     */
    private Node root;
    private String filePath;
    
    /**
     * Creates a new Tree
     * and fill it with
     * all registered users
     */
    public UsersBST() {
        this.root = null;
        filePath = "src/database/users.txt";
        loadActiveUsers();
    }
    
    public String getFilePath() {
        return this.filePath;
    }
    
    /**
     * Inserts a new User to the tree
     * 
     * @param user
     *  User to insert
     * @param isVip 
     *  true if User is a VipUser
     *  false if User is a DefaultUser
     */
    public void insert(User user, boolean isVip) {
        Node node = new Node(user,isVip);
        if(this.root == null) {
            root = node;
            return;
        }
        
        Node current = root;
        Node parent = null;
        while(current != null) {
            parent = current;           
            if(user.id < current.user.id) {
                current = current.left;
            }
            else {
                if(user.id > current.user.id) {
                    current = current.right;
                }
            }
        }
        if(user.id > parent.user.id) {
            parent.right = node;
        } else {
            parent.left = node;
        }
    }
    
    /**
     * Finds a user in the Tree
     * by a given Id
     * 
     * used to check if any user
     * is registered with the given id
     * 
     * @param id
     *   id to find
     * @return 
     *  true: there is a user with the given id
     *  false: there's no user with the given id
     */
    public boolean findUser(int id) {
        Node current = root;
        while(current != null) {
            if(current.user.id == id) {
                return true;
            }
            else if(current.user.id > id) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }
    
    /**
     * Check if the Tree
     * has a user with the given username.
     * 
     * Used to check <b>username disponibility</b>
     * on sign up screen.
     * 
     * @param name
     *  username to find in the tree
     * @return 
     *  true: there is a user registered with the given username
     *  false: there is no user registered with the given username
     */
    public boolean hasUser(String name) {
        Node current = root;
        while(current != null) {
            if(current.user.username.compareToIgnoreCase(name) == 0) {
                return true;
            } else if(current.user.username.compareToIgnoreCase(name) > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }
    
    /**
     * Get the id of a user with the given name
     * 
     * @param name
     *  username to find
     * @return
     *  int: the Id of the user found
     * 
     * @throws LoginException 
     *  when passed a name that does not correspond to a user in the tree.
     */
    public int getUserId(String name) throws LoginException {        
        try {
            String path = "src/database/users.txt";
            int numberOfUsers = Connection.getInstance().countFileLines(path);
            String[] userslist = Connection.getInstance().openFile(path);
            String[] data = new String[4];
            
            for(int index = 0; index < numberOfUsers; index++) {
                data = userslist[index].split(",");
                if(data[1].equals(name))
                    return index+1;
            }
        } catch (IOException e) {
            throw new LoginException("Error trying to connect. try again later.");
        }
        throw new LoginException("User not found.");
    }
    
    /**
     * Get a user object from the Tree
     * 
     * @param username
     *  the name of the user to get
     * @param password
     *  the password of the user to get
     * @return
     *  a new User Object
     *  that can be a Default or Vip User.
     * 
     * @throws LoginException 
     *  if any of the params are incorrect
     *  the user is not found on the tree.
     */
    public User getUser(String username, String password) throws LoginException {
        
        int id = getUserId(username);
        
        Node current = root;
        
        while(current != null) {
            if(current.user.id == id) {
                if(current.user.getUsername().equals(username)) {
                    if(current.user.getPassword().equals(password)) {
                        if(current.isVip)
                            return new VipUser(current.user.id, current.user.username, current.user.password);
                        else
                            return new DefaultUser(current.user.id, current.user.username, current.user.password);
                    } else {
                        throw new LoginException("Incorrect Password");
                    }
                }
            }
            else {
                if(current.user.id > id) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
        }        
        throw new LoginException("User not found.");
    }
    
    /**
     * Deletes a node from the Tree
     * by the given id
     * 
     * @param id
     *  Id to find which node to delete
     * @return 
     *  true if node was deleted
     *  false if no node was deleted
     */
    public boolean delete(int id){
		Node parent = root;
		Node current = root;
		boolean isLeftChild = false;
		while(current.user.id != id){
			parent = current;
			if(current.user.id > id){
				isLeftChild = true;
				current = current.left;
			} else {
				isLeftChild = false;
				current = current.right;
			}
			if(current == null){
				return false;
			}
		}
		//if i am here that means we have found the node
                
		//Case 1: if node to be deleted has no children
		if(current.left == null && current.right == null){
			if(current == root){
                            root = null;
			}
			if(isLeftChild == true){
				parent.left = null;
			} else {
				parent.right = null;
			}
		}
                
		// Case 2 : if node to be deleted has only one child
		else if(current.right == null){
			if(current == root){
				root = current.left;
			} else if(isLeftChild){
				parent.left = current.left;
			} else {
				parent.right = current.left;
			}
		}
		else if(current.left == null){
			if(current == root){
				root = current.right;
			} else if(isLeftChild){
				parent.left = current.right;
			} else{
				parent.right = current.right;
			}
		} else if(current.left != null && current.right != null){
			
			//now we have found the minimum element in the right sub tree
			Node successor	 = getSuccessor(current);
			if(current == root){
				root = successor;
			} else if(isLeftChild){
				parent.left = successor;
			} else {
				parent.right = successor;
			}			
			successor.left = current.left;
		}		
		return true;		
	}
	
	public Node getSuccessor(Node deleteNode){
		Node successsor = null;
		Node successsorParent = null;
		Node current = deleteNode.right;
		while(current != null) {
			successsorParent = successsor;
			successsor = current;
			current = current.left;
		}
		// check if successor has the right child, it cannot have left child for sure
		// if it does have the right child, add it to the left of successorParent. successsorParent
		if(successsor != deleteNode.right){
			successsorParent.left = successsor.right;
			successsor.right = deleteNode.right;
		}
		return successsor;
}
    
    /**
     * Fills the Tree
     * with all registered
     * users in the application
     */
    public void loadActiveUsers() {
        try {
            String[] file = new String[Connection.getInstance().countFileLines(this.filePath)];
            file = Connection.getInstance().openFile(this.filePath);
            String[] data = new String[4];
            for(int iterator = 0; iterator < file.length; iterator++) {
                data = file[iterator].split(",");
                if(data[3].equals("true")) {
                    insert(new VipUser((iterator+1), data[1], data[2]), true);
                } else {
                    insert(new DefaultUser((iterator+1), data[1], data[2]), false);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/**
 * Node
 * 
 * represents a Node
 * for the Binary Search Tree
 * of Users
 * 
 * @author Paulo
 */
class Node {
    User user;
    boolean isVip;
    
    /**
     * Node left, Node right
     * pointers to left and right children
     */
    Node left;
    Node right;
    
    public Node(User user, boolean isVip) {
        this.user = user;
        this.isVip = isVip;
        this.left = null;
        this.right = null;
    }
}