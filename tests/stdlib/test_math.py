import unittest

from ..utils import TranspileTestCase


class MathModuleTests(TranspileTestCase):
    def test_fabs(self):
        self.assertCodeExecution("""
            import math
            print(math.fabs(2))
            print(math.fabs(-2.0))
            print(math.fabs(0))
            """)

    @unittest.expectedFailure
    def test_fabs_non_float_arg(self):
        self.assertCodeExecution("""
            import math
            print(math.fabs("hej"))
        """)

    @unittest.expectedFailure
    def test_fabs_wrong_float_impl(self):
        self.assertCodeExecution("""
            import math
            class Testtest:
                def __float__():
                    return "not float"
            print(math.fabs(Testtest()))
        """)
