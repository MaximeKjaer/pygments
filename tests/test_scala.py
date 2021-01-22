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
        (Token.Punctuation, ':'),
        (Token.Text, ' '),
        (Token.Name.Class, 'Char'),
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
        (Token.Punctuation, ')'),
        (Token.Punctuation, ':'),
        (Token.Text, ' '),
        (Token.Name.Class, 'Int'),
        (Token.Text, ' '),
        (Token.Operator, '='),
        (Token.Text, ' '),
        (Token.Operator, '???'),
        (Token.Text, '\n'),
    ]
    assert list(lexer.get_tokens(fragment)) == tokens

def test_package_name(lexer):
    fragment = 'package p1.p2:\n'
    tokens = [
        (Token.Keyword, 'package'),
        (Token.Text, ' '),
        (Token.Name.Namespace, 'p1'),
        (Token.Punctuation, '.'),
        (Token.Name, 'p2'),
        (Token.Punctuation, ':'),
        (Token.Text, '\n'),
    ]
    assert list(lexer.get_tokens(fragment)) == tokens

def test_symbol_followed_by_op(lexer):
    fragment = "'symbol*\n"
    tokens = [
        (Token.Literal.String.Symbol, "'symbol"),
        (Token.Operator, '*'),
        (Token.Text, '\n'),
    ]
    assert list(lexer.get_tokens(fragment)) == tokens

def test_symbol_name_ending_with_star(lexer):
    fragment = "'symbol_*\n"
    tokens = [
        (Token.Literal.String.Symbol, "'symbol_*"),
        (Token.Text, '\n'),
    ]
    assert list(lexer.get_tokens(fragment)) == tokens

def test_colon_colon_function_name(lexer):
    fragment = 'def ::(xs: List[T]): List[T] = ::(x, xs)\n'
    tokens = [
        (Token.Keyword, 'def'),
        (Token.Text, ' '),
        (Token.Name.Function, '::'),
        (Token.Punctuation, '('),
        (Token.Name, 'xs'),
        (Token.Punctuation, ':'),
        (Token.Text, ' '),
        (Token.Name.Class, 'List'),
        (Token.Punctuation, '['),
        (Token.Name.Class, 'T'),
        (Token.Punctuation, ']'),
        (Token.Punctuation, ')'),
        (Token.Punctuation, ':'),
        (Token.Text, ' '),
        (Token.Name.Class, 'List'),
        (Token.Punctuation, '['),
        (Token.Name.Class, 'T'),
        (Token.Punctuation, ']'),
        (Token.Text, ' '),
        (Token.Operator, '='),
        (Token.Text, ' '),
        (Token.Name, '::'),
        (Token.Punctuation, '('),
        (Token.Name, 'x'),
        (Token.Punctuation, ','),
        (Token.Text, ' '),
        (Token.Name, 'xs'),
        (Token.Punctuation, ')'),
        (Token.Text, '\n'),
    ]
    assert list(lexer.get_tokens(fragment)) == tokens

def test_invalid_symbol_and_invalid_char(lexer):
    fragment = "'1 //'\n"
    tokens = [
        (Token.Error, "'"),
        (Token.Literal.Number.Integer, '1'),
        (Token.Text, ' '),
        (Token.Comment.Single, "//'\n"),
    ]
    assert list(lexer.get_tokens(fragment)) == tokens

def test_prepend_operator(lexer):
    fragment = 'a +: b\n'
    tokens = [
        (Token.Name, 'a'),
        (Token.Text, ' '),
        (Token.Operator, '+:'),
        (Token.Text, ' '),
        (Token.Name, 'b'),
        (Token.Text, '\n'),
    ]
    assert list(lexer.get_tokens(fragment)) == tokens

def test_open_soft_keyword(lexer):
    fragment = 'val open = true\n'
    tokens = [
        (Token.Keyword.Declaration, 'val'),
        (Token.Text, ' '),
        (Token.Name, 'open'), # not Token.Keyword in this context
        (Token.Text, ' '),
        (Token.Operator, '='),
        (Token.Text, ' '),
        (Token.Keyword.Constant, 'true'),
        (Token.Text, '\n'),
    ]
    assert list(lexer.get_tokens(fragment)) == tokens

def test_underscore_name(lexer):
    fragment = 'val head = _head\n'
    tokens = [
        (Token.Keyword.Declaration, 'val'),
        (Token.Text, ' '),
        (Token.Name, 'head'),
        (Token.Text, ' '),
        (Token.Operator, '='),
        (Token.Text, ' '),
        (Token.Name, '_head'),
        (Token.Text, '\n'),
    ]
    assert list(lexer.get_tokens(fragment)) == tokens

def test_end_val(lexer):
    fragment = 'end val\n'
    tokens = [
        (Token.Keyword, 'end'),
        (Token.Text, ' '),
        (Token.Keyword, 'val'), # not Token.Name
        (Token.Text, '\n'),
    ]
    assert list(lexer.get_tokens(fragment)) == tokens

def test_end_valx(lexer):
    fragment = 'end valx\n'
    tokens = [
        (Token.Keyword, 'end'),
        (Token.Text, ' '),
        (Token.Name.Namespace, 'valx'), # not Token.Keyword
        (Token.Text, '\n'),
    ]
    assert list(lexer.get_tokens(fragment)) == tokens

def test_qualified_name(lexer):
    fragment = 'a.b.c\n'
    tokens = [
        (Token.Name, 'a'),
        (Token.Punctuation, '.'),
        (Token.Name, 'b'),
        (Token.Punctuation, '.'),
        (Token.Name, 'c'),
        (Token.Text, '\n'),
    ]
    assert list(lexer.get_tokens(fragment)) == tokens

def test_qualified_name_class(lexer):
    fragment = 'a.b.C\n'
    tokens = [
        (Token.Name, 'a'),
        (Token.Punctuation, '.'),
        (Token.Name, 'b'),
        (Token.Punctuation, '.'),
        (Token.Name.Class, 'C'),
        (Token.Text, '\n'),
    ]
    assert list(lexer.get_tokens(fragment)) == tokens


def test_import_path(lexer):
    fragment = 'import a.b.c\n'
    tokens = [
        (Token.Keyword, 'import'),
        (Token.Text, ' '),
        (Token.Name.Namespace, 'a'),
        (Token.Punctuation, '.'),
        (Token.Name.Namespace, 'b'),
        (Token.Punctuation, '.'),
        (Token.Name, 'c'),
        (Token.Text, '\n'),
    ]
    assert list(lexer.get_tokens(fragment)) == tokens

def test_function_operator_name(lexer):
    fragment = 'def < (y: String): Boolean\n'
    tokens = [
        (Token.Keyword, 'def'),
        (Token.Text, ' '),
        (Token.Name.Function, '<'), # not Operator
        (Token.Text, ' '),
        (Token.Punctuation, '('),
        (Token.Name, 'y'),
        (Token.Punctuation, ':'),
        (Token.Text, ' '),
        (Token.Name.Class, 'String'),
        (Token.Punctuation, ')'),
        (Token.Punctuation, ':'),
        (Token.Text, ' '),
        (Token.Name.Class, 'Boolean'),
        (Token.Text, '\n'),
    ]
    assert list(lexer.get_tokens(fragment)) == tokens
