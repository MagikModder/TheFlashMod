package fiskfille.utils;

import java.util.Arrays;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import fiskfille.utils.common.time.Tickrate;

public class UtilsClassTransformer implements IClassTransformer
{
	private static final String[] classesBeingTransformed =
	{
		"net.minecraft.server.MinecraftServer"
	};

	@Override
	public byte[] transform(String name, String transformedName, byte[] classBeingTransformed)
	{
		boolean isObfuscated = !name.equals(transformedName);
		int index = Arrays.asList(classesBeingTransformed).indexOf(transformedName);
		return index != -1 ? transform(index, classBeingTransformed, isObfuscated) : classBeingTransformed;
	}

	private static byte[] transform(int index, byte[] classBeingTransformed, boolean isObfuscated)
	{
		System.out.println("Transforming: " + classesBeingTransformed[index]);
		
		try
		{
			ClassNode classNode = new ClassNode();
			ClassReader classReader = new ClassReader(classBeingTransformed);
			classReader.accept(classNode, 0);

			switch(index)
			{
			case 0:
				transformMinecraftServer(classNode, isObfuscated);
				break;
			}

			ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
			classNode.accept(classWriter);
			return classWriter.toByteArray();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return classBeingTransformed;
	}

	private static void transformMinecraftServer(ClassNode minecraftServerClass, boolean isObfuscated)
	{		
		final String RUN = "run";
		final String RUN_DESC = "()V";

		for (MethodNode method : minecraftServerClass.methods)
		{			
			if (method.name.equals(RUN) && method.desc.equals(RUN_DESC))
			{
				InsnList list = new InsnList();

				for (AbstractInsnNode node : method.instructions.toArray())
				{
					if (node instanceof LdcInsnNode)
					{
						LdcInsnNode ldcNode = (LdcInsnNode)node;
						
						if ((ldcNode.cst instanceof Long) && ((Long)ldcNode.cst == 50L))
						{
							list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(UtilsClassTransformer.class), "getMiliSecondsPerTick", "()J", false));
							continue;
						}
					}

					list.add(node);				
				}

				method.instructions.clear();
				method.instructions.add(list);
			}
		}
	}
	
	public static long getMiliSecondsPerTick()
	{
		return Tickrate.MILISECONDS_PER_TICK;
	}
}
