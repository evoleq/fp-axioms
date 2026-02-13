package org.evoleq.axioms.processor.value

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.validate
import org.evoleq.axioms.definition.Value

class ValueProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(Value::class.qualifiedName!!)
        val unprocessed = mutableListOf<KSAnnotated>()

        symbols.filter { it is KSClassDeclaration && it.validate() }.forEach { symbol ->
            val classDecl = symbol as KSClassDeclaration
            val packageName = classDecl.packageName.asString()
            val className = classDecl.simpleName.asString()

            val containingFile = symbol.containingFile ?: run {
                unprocessed.add(symbol)
                return@forEach
            }
            val file = codeGenerator.createNewFile(
                dependencies = Dependencies(false, containingFile),
                packageName = packageName,
                fileName = "${className}Value"
            )

            file.use { outputStream ->
                outputStream.write(
                    """
                    package $packageName

                    import kotlin.reflect.KProperty

                    operator fun $className.getValue(thisRef: Any?, property: KProperty<*>): String {
                        return id
                    }
                """.trimIndent().toByteArray()
                )
            }
        }

        return unprocessed
    }
}

