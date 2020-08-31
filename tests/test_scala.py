# -*- coding: utf-8 -*-
"""
    Scala Tests
    ~~~~~~~~~~~

    :copyright: Copyright 2006-2020 by the Pygments team, see AUTHORS.
    :license: BSD, see LICENSE for details.
"""

import pytest

from pygments.lexers import ScalaLexer
from pygments.token import Token

import re

@pytest.fixture(scope='module')
def lexer():
    yield ScalaLexer()

def test_float_with_exponents(lexer):
    fragment = '.1e12 .1e+34 .1e-56 .1e12f\n'
    tokens = [
        (Token.Literal.Number.Float, '.1e12'),
        (Token.Text, ' '),
        (Token.Literal.Number.Float, '.1e+34'),
        (Token.Text, ' '),
        (Token.Literal.Number.Float, '.1e-56'),
        (Token.Text, ' '),
        (Token.Literal.Number.Float, '.1e12f'),
        (Token.Text, '\n'),
    ]
    assert list(lexer.get_tokens(fragment)) == tokens

def test_default_parameter(lexer):
    fragment = "def f(using y: Char = if true then 'a' else 2): Int = ???\n"
    tokens = [
        (Token.Keyword, 'def'),
        (Token.Text, ' '),
        (Token.Name.Function, 'f'),
        (Token.Punctuation, '('),
        (Token.Keyword, 'using'),
        (Token.Text, ' '),
        (Token.Name, 'y'),
        (Token.Operator, ':'),
        (Token.Text, ' '),
        (Token.Keyword.Type, 'Char'),
        (Token.Text, ' '),
        (Token.Operator, '='),
        (Token.Text, ' '),
        (Token.Keyword, 'if'),
        (Token.Text, ' '),
        (Token.Keyword.Constant, 'true'),
        (Token.Text, ' '),
        (Token.Keyword, 'then'),
        (Token.Text, ' '),
        (Token.Literal.String.Char, "'a'"),
        (Token.Text, ' '),
        (Token.Keyword, 'else'),
        (Token.Text, ' '),
        (Token.Literal.Number.Integer, '2'),
        (Token.Operator, ')'),
        (Token.Punctuation, ':'),
        (Token.Text, ' '),
        (Token.Keyword.Type, 'Int'),
        (Token.Text, ' '),
        (Token.Operator, '='),
        (Token.Text, ' '),
        (Token.Operator, '???'),
        (Token.Text, '\n'),
    ]
    assert list(lexer.get_tokens(fragment)) == tokens