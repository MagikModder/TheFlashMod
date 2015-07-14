//package fiskfille.flash.asm;
//
//import java.util.Iterator;
//
//import net.minecraft.launchwrapper.IClassTransformer;
//
//import org.objectweb.asm.ClassReader;
//import org.objectweb.asm.ClassWriter;
//import org.objectweb.asm.Opcodes;
//import org.objectweb.asm.tree.AbstractInsnNode;
//import org.objectweb.asm.tree.ClassNode;
//import org.objectweb.asm.tree.FieldInsnNode;
//import org.objectweb.asm.tree.InsnList;
//import org.objectweb.asm.tree.LdcInsnNode;
//import org.objectweb.asm.tree.MethodNode;
//
//public class FlashClassTransformer implements IClassTransformer
//{
//	@Override
//	public byte[] transform(String name, String name2, byte[] bytes)
//	{
//		if (bytes == null)
//		{
//			return null;
//		}
//
//		try
//		{
//			if (name.equals("net.minecraft.server.MinecraftServer"))
//			{
//				return patchServerTickrate(bytes);
//			}
//			else if (name.equals("net.minecraft.network.NetworkManager") || (name.equals("ej")))
//			{
//				return patchNetworkManager(bytes);
//			}
//		}
//		catch (Exception ex)
//		{
//			ex.printStackTrace();
//		}
//
//		return bytes;
//	}
//
//	public byte[] patchServerTickrate(byte[] bytes)
//	{
//		System.out.println("Applying ASM to Minecraft methods...");
//
//		ClassNode classNode = new ClassNode();
//		ClassReader classReader = new ClassReader(bytes);
//		classReader.accept(classNode, 0);
//
//		Iterator<MethodNode> methods = classNode.methods.iterator();
//		
//		while (methods.hasNext())
//		{
//			MethodNode method = methods.next();
//			
//			if ((method.name.equals("run")) && (method.desc.equals("()V")))
//			{
//				InsnList list = new InsnList();
//				
//				for (AbstractInsnNode node : method.instructions.toArray())
//				{
//					if (node instanceof LdcInsnNode)
//					{
//						LdcInsnNode ldcNode = (LdcInsnNode)node;
//						
//						if ((ldcNode.cst instanceof Long) && ((Long)ldcNode.cst == 50L))
//						{
//							list.add(new FieldInsnNode(Opcodes.GETSTATIC, "fiskfille/flash/common/time/TimeHelper", "MILISECONDS_PER_TICK", "J"));
//							continue;
//						}
//					}
//
//					list.add(node);
//				}
//
//				method.instructions.clear();
//				method.instructions.add(list);
//			}
//		}
//
//		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
//		classNode.accept(writer);
//		return writer.toByteArray();
//	}
//
//	public byte[] patchNetworkManager(byte[] bytes)
//	{
//		System.out.println("Applying new timeout value...");
//
//		ClassNode classNode = new ClassNode();
//		ClassReader classReader = new ClassReader(bytes);
//		classReader.accept(classNode, 0);
//
//		Iterator<MethodNode> methods = classNode.methods.iterator();
//		
//		while (methods.hasNext())
//		{
//			MethodNode method = methods.next();
//			
//			if ((method.name.equals("a") || method.name.equals("provideLanClient")) && (method.desc.startsWith("(Ljava/net/InetAddress;I)")))
//			{
//				InsnList list = new InsnList();
//				
//				for (AbstractInsnNode node : method.instructions.toArray())
//				{
//					if (node instanceof LdcInsnNode)
//					{
//						LdcInsnNode ldcNode = (LdcInsnNode)node;
//						
//						if ((ldcNode.cst instanceof Integer) && ((Integer)ldcNode.cst == 20))
//						{
//							System.out.println("Timeout set.");
//							ldcNode.cst = 50000;
//						}
//					}
//
//					list.add(node);
//				}
//
//				method.instructions.clear();
//				method.instructions.add(list);
//			}
//
//		}
//
//		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
//		classNode.accept(writer);
//		return writer.toByteArray();
//	}
//}
