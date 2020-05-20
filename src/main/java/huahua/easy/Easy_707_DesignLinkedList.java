package huahua.easy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class Easy_707_DesignLinkedList {

	public class MyLinkedList {
		Node head;
		int size;
		
		public void addAtHead(int value) {
			Node n = new Node(value, head);
			head = n;
			size++;
		}
		
		public int get(int index) {
			if (head == null)
				return -1;
			if (index == 1)
				return head.val;
			int i = 1;
			Node n = head;
			while(n.next != null) {
				if (i == index)
					return n.val;
				n = n.next;
				i++;
			}
			return n.val;
		}
		
		public void addAtTail(int value) {
			Node n = head;
			if (n == null) {
				head = new Node(value, null);
				size++;
				return;
			}
			
			while(n.next != null) {
				n = n.next;
			}
			n.next = new Node(value, null);
			size++;
		}
		
		public void addAtIndex(int index, int value) throws IllegalArgumentException{
			if (index < 1)
				throw new IllegalArgumentException("Index " + index + " is illegal, should be greater than 1");
			Node n = head;
			if (n == null && index == 1) {
				head = new Node(value, null);
				size++;
				return;
			} else if (n == null && index > 0) {
				throw new IllegalArgumentException(
						"Cannot add node at empty linked list at index " + index);
			}
			if (index == 1) {
				addAtHead(value);
				return;
			}
			
			int i = 1;
			Node previous = null;
			while(n.next != null) {
				if (i == index - 1) {
					previous = n;
				} else if (i == index){
					Node newNode = new Node(value, n);
					previous.next = newNode;
					size++;
					return;					
				}
				n = n.next;
				i++;
			}
			if (i == index - 1) {
				addAtTail(value);
			}
			
		}
		
		public void deleteAtIndex(int index) throws IllegalArgumentException{
			if (index < 1)
				throw new IllegalArgumentException("Index " + index + " is illegal, should be greater than 1");
			Node n = head;
			if (n == null) {
				throw new IllegalArgumentException("Cannot delete node at empty linked list");
			}
			
			if (index == 1) {
				head = head.next;
				size--;
				return;
			}
			
			int i = 1;
			Node previous = null;
			while(n.next != null) {
				if (i == index - 1) {
					previous = n;
				} else if (i == index) {
					previous.next = n.next;
					size--;
					return;
				}
				n = n.next;
				i++;
			}
			if (size == index) {
				previous.next = null;
				size--;
			}
		}
		
		public void print() {
			Node n = head;
			if (head == null)
				System.out.println("LinkedList is null.");
			StringBuilder sb = new StringBuilder(n.val + "->");
			while(n.next != null) {
				n = n.next;
				sb.append(n.val + "->");
			}
			
			System.out.println(
					sb.toString().substring(0, sb.length() - 2) 
					+ ", size: " + size);
		}
	}
	
	public class Node {
		int val;
		Node next;
		public Node(int val, Node next) {
			this.val = val;
			this.next = next;
		}
	}
	
	
	@Test
	public void test_addHead_EmptyList() {
		MyLinkedList linkedList = new MyLinkedList();
		linkedList.addAtHead(1);
		assertTrue(linkedList.get(1) == 1);
	}
	
	@Test
	public void test_addTail_EmptyList() {
		MyLinkedList linkedList = new MyLinkedList();
		linkedList.addAtTail(1);
		assertTrue(linkedList.get(1) == 1);
	}
	
	@Test
	public void test_addIndex1_EmptyList() {
		MyLinkedList linkedList = new MyLinkedList();
		linkedList.addAtIndex(1, 1);
		assertTrue(linkedList.get(1) == 1);
	}
	
	@Test
	public void test_addIndex_1() {
		MyLinkedList linkedList = setup();
		int sizeBefore = linkedList.size;
		linkedList.addAtIndex(1, 10);
		assertTrue(linkedList.size == sizeBefore + 1);
		assertEquals(linkedList.get(1), 10);
		assertEquals(linkedList.get(2), 1);
		assertEquals(linkedList.get(3), 2);
		assertEquals(linkedList.get(4), 3);
		assertEquals(linkedList.get(5), 4);
		assertEquals(linkedList.get(6), 5);
	}
	
	@Test
	public void test_addIndex_1_2() {
		MyLinkedList linkedList = setup();
		int sizeBefore = linkedList.size;
		linkedList.addAtIndex(1, 10);
		linkedList.addAtIndex(2, 11);
		assertTrue(linkedList.size == sizeBefore + 2);
		assertEquals(linkedList.get(1), 10);
		assertEquals(linkedList.get(2), 11);
		assertEquals(linkedList.get(3), 1);
		assertEquals(linkedList.get(4), 2);
		assertEquals(linkedList.get(5), 3);
		assertEquals(linkedList.get(6), 4);
		assertEquals(linkedList.get(7), 5);
	}
	
	@Test
	public void test_addIndex_tail() {
		MyLinkedList linkedList = setup();
		int sizeBefore = linkedList.size;
		linkedList.addAtIndex(linkedList.size + 1, 10);
		assertTrue(linkedList.size == sizeBefore + 1);
		assertEquals(linkedList.get(1), 1);
		assertEquals(linkedList.get(2), 2);
		assertEquals(linkedList.get(3), 3);
		assertEquals(linkedList.get(4), 4);
		assertEquals(linkedList.get(5), 5);
		assertEquals(linkedList.get(6), 10);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_addIndex0_EmptyList() {
		MyLinkedList linkedList = new MyLinkedList();
		linkedList.addAtIndex(0, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_deleteIndex0_EmptyList() {
		MyLinkedList linkedList = new MyLinkedList();
		linkedList.deleteAtIndex(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_addIndex0_NotEmptyList() {
		MyLinkedList linkedList = setup();
		linkedList.addAtIndex(0, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_deleteIndex0_NotEmptyList() {
		MyLinkedList linkedList = setup();
		linkedList.deleteAtIndex(0);
	}
	
	@Test
	public void test_deleteIndex_1() {
		MyLinkedList linkedList = setup();
		int sizeBefore = linkedList.size;
		linkedList.deleteAtIndex(1);
		assertTrue(linkedList.size == sizeBefore - 1);
		assertTrue(linkedList.get(1) == 2);
		assertTrue(linkedList.get(2) == 3);
		assertTrue(linkedList.get(3) == 4);
		assertTrue(linkedList.get(4) == 5);
	}
	
	@Test
	public void test_deleteIndex_1_2() {
		MyLinkedList linkedList = setup();
		int sizeBefore = linkedList.size;
		linkedList.deleteAtIndex(1);
		linkedList.deleteAtIndex(1);
		assertTrue(linkedList.size == sizeBefore - 2);
		assertTrue(linkedList.get(1) == 3);
		assertTrue(linkedList.get(2) == 4);
		assertTrue(linkedList.get(3) == 5);
	}
	
	@Test
	public void test_deleteIndex_Last() {
		MyLinkedList linkedList = setup();
		int sizeBefore = linkedList.size;
		linkedList.deleteAtIndex(sizeBefore);
		assertTrue(linkedList.size == sizeBefore - 1);
		assertTrue(linkedList.get(1) == 1);
		assertTrue(linkedList.get(2) == 2);
		assertTrue(linkedList.get(3) == 3);
		assertTrue(linkedList.get(4) == 4);
	}
	
	
	public MyLinkedList setup() {
		MyLinkedList linkedList = new MyLinkedList();
		linkedList.addAtTail(1);
		linkedList.addAtTail(2);
		linkedList.addAtTail(3);
		linkedList.addAtTail(4);
		linkedList.addAtTail(5);
		return linkedList;
	}
}
